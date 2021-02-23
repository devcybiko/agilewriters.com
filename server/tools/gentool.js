const glstools = require("glstools");
const gprocs = glstools.procs;
const gfiles = glstools.files;
const gstrings = glstools.strings;

let rndtxt;
let config = {
    entities: "./entities",
    rndtxt: "./lorum.txt",
    templates: "./templates",
    sqldir: "./sql",
    javadir: "./java"
}
let sqlTypes = {
    "string": "VARCHAR",
    "int": "INT"
}
let javaTypes = {
    "string": "String",
    "int": "Integer",
    "long": "Long"
}
let hash;
let entity;
let macros;
let template;

function capitalize(s) {
    return s.charAt(0).toUpperCase() + s.slice(1)
}
function main() {
    let opts = gprocs.args("");
    let infile = opts.files[0];
    entity = gfiles.readJSON(infile);
    hash = createHash(entity);
    console.log({ entity, hash });

    readRandomText();

    genEntity("Entity.java", config.javadir + "/" + hash.names + "/" + hash.Name + ".java");
    genEntity("EntityUpdateRequest.java", config.javadir + "/" + hash.names + "/dtos/" + hash.Name + "UpdateRequest.java");
    genEntity("EntityRepository.java", config.javadir + "/" + hash.names + "/" + hash.Name + "Repository.java");
    genEntity("EntityRestController.java", config.javadir + "/" + hash.names + "/" + hash.Name + "RestController.java");
    genEntity("EntityService.java", config.javadir + "/" + hash.names + "/" + hash.Name + "Service.java");
    genEntity("EntityServiceImpl.java", config.javadir + "/" + hash.names + "/" + hash.Name + "ServiceImpl.java");
    genSQL("entity.sql");
}

function readRandomText() {
    let text = gfiles.read(config.rndtxt);
    rndtxt = text.split(/[^\w]/).filter(Boolean);
}

function replaceAllValues() {
    console.log({ hash, template });
    let result = template;
    for (var name of Object.keys(hash)) {
        result = gstrings.replaceAll(result, `[{]${name}[}]`, hash[name]);
    }
    console.log({ result });
    return result;
}

function getTemplate(tname) {
    macros = {};
    let fname = `./${config.templates}/${tname}`;
    console.log(fname);
    template = gfiles.read(fname);
    console.log(template);
    let matches = template.matchAll(/[{](.*):(`.*`)[}]/g);
    let newTemplate = "";
    let last = 0;
    for (var match of matches) {
        console.log({ match });
        macros[match[1]] = match[2];
        newTemplate += template.substring(last, match.index);
        newTemplate += "{" + match[1] + "}";
        last = match.index + match[0].length;
    }
    newTemplate += template.substring(last);
    expandMacros();
    return newTemplate;
}

function createHash(entity) {
    hash = {};
    hash.name = entity.name;
    hash.names = hash.name + "s";
    hash.Name = hash.name[0].toUpperCase() + hash.name.substring(1);
    hash.Names = hash.names[0].toUpperCase() + hash.names.substring(1);
    return hash;
}

function parseDataType(s, types) {
    let opts = s.split(",");
    let type = opts[0];
    let len = opts[1] || "";
    let unique = opts[2];
    let secret = opts[3];
    let Type = types[type];
    return { type, len, unique, Type, secret };
}

function genSQL(tfile) {
    function computeCols() {
        let cols = Object.keys(entity.fields).map(key => key);
        return cols.join(",");
    }
    function computeBody() {
        let uniqueFields = [];
        let body = "";
        let comma = "";
        for (var field of Object.keys(entity.fields)) {
            let opts = parseDataType(entity.fields[field], sqlTypes);
            if (opts.len) opts.len = `(${opts.len})`;
            if (opts.unique) uniqueFields.push(field);
            body += `${comma}${field} ${opts.Type} ${opts.len}`;
            comma = ",\n    ";
        }
        for (var uniqueField of uniqueFields) {
            body += `${comma}UNIQUE(${uniqueField})`;
        }
        return body;
    }
    template = getTemplate(tfile);
    hash.cols = computeCols(entity);
    hash.body = computeBody(entity);
    let sql = replaceAllValues(hash, template);
    console.log(sql);
    let outfile = config.sqldir + "/" + hash.names + ".sql";
    gfiles.write(outfile, sql);
    return sql;
}

function expandMacros() {
    for (let key of Object.keys(macros)) {
        let macro = macros[key];
        let decl = "";
        for (var field of Object.keys(entity.fields)) {
            let opts = parseDataType(entity.fields[field], javaTypes);
            let Field = capitalize(field);
            decl += eval(macro);
        }
        hash[key] = decl;
    }
}


function computePutAssignments() {
    let s = "";
    for (var field of Object.keys(entity.fields)) {
        let opts = parseDataType(entity.fields[field], javaTypes);
        let Field = capitalize(field);
        s += `\n    ${hash.name}.get().set${Field}(request.get${Field}());`;
    }
    return s;
}

function computePostAssignments() {
    let s = "";
    for (var field of Object.keys(entity.fields)) {
        let opts = parseDataType(entity.fields[field], javaTypes);
        let Field = capitalize(field);
        s += `\n    ${hash.name}.set${Field}(request.get${Field}());`;
    }
    return s;
}

function computeRedact() {
    let s = "";
    for (var field of Object.keys(entity.fields)) {
        let opts = parseDataType(entity.fields[field], javaTypes);
        let Field = capitalize(field);
        if (opts.secret) s += `\n    result.set${Field}(null);`;
    }
    return s;
}

function genEntity(tfile, outfile) {
    createHash(entity);
    template = getTemplate(tfile);
    hash.redact = computeRedact();
    let java = replaceAllValues(hash, template);
    console.log(java);
    gfiles.write(outfile, java);
    return java;
}

main();
