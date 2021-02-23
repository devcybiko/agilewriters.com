package com.agilewriters.workshop.{names}.dtos;

public class {Name}UpdateRequest {
    {declarations:`private ${opts.Type} ${field};\n`}
    {accessors:`public ${opts.Type} get${Field}() { return this.${field};}\npublic void set${Field}(${opts.Type} ${field}) { this.${field} = ${field}; }\n`}

    @Override
    public String toString() {
        String s = "{tostring:`${field}="+${field}+",`}";
        return "{Name} [" +s+ "]";
    }
}