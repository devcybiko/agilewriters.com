package com.agilewriters.workshop.{names};

import java.util.List;
import java.util.Optional;

import com.agilewriters.workshop.{names}.dtos.{Name}UpdateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class {Name}RestController {

    private static final Logger LOGGER = LoggerFactory.getLogger({Name}RestController.class);

    @Autowired
    {Name}Repository {Name}Repository;

    @GetMapping("/{name}/{id}")
    public {Name} find{Name}(@PathVariable("id") Long id) {
        LOGGER.info("Inside find{Name}() for id: " + id);
        return {Name}Repository.findById(id).get();
    }

    @GetMapping("/{name}")
    public List<{Name}> findAll{Names}() {
        LOGGER.info("Inside findAll{Names}()");
        return {Name}Repository.findAll();
    }

    @PutMapping("/{name}/{id}")
    public {Name} update{Name}(@PathVariable("id") Long id, @RequestBody {Name}UpdateRequest request) {
        LOGGER.info("Inside update{Name}() for " + request);
        Optional<{Name}> {name} = {Name}Repository.findById(id);
{putassignments:`\n    ${hash.name}.get().set${Field}(request.get${Field}());`}
		LOGGER.info("Updating {Name}");
		{Name} result = {Name}Repository.save({name}.get());
        {redact}
        return result;
	}

    @PostMapping("/{name}")
    public {Name} insert{Name}(@RequestBody {Name}UpdateRequest request) {
        LOGGER.info("Inside insert{Name}() for " + request);
        {Name} {name} = new {Name}();
{postassignments:`\n    ${hash.name}.set${Field}(request.get${Field}());`}
		LOGGER.info("Inserting {Name}");
		{Name} result =  {Name}Repository.save({name});
        {redact}
        return result;
	}

}
