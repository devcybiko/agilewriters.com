package com.agilewriters.workshop.{names};

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class {Name}ServiceImpl implements {Name}Service {

    @Autowired
    private {Name}Repository repository;

    @Override
    public {Name} save{Name}({Name} {Name}) {
        return repository.save({Name});
    }

    @Override
    public {Name} update{Name}({Name} {Name}) {
        return repository.save({Name});
    }

    @Override
    public void delete{Name}({Name} {Name}) {
        repository.delete({Name});
    }

    @Override
    public Optional<{Name}> get{Name}ById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<{Name}> getAll{Names}() {
		return (List<{Name}>) repository.findAll();
	}

	public {Name}Repository getRepository() {
		return repository;
	}

	public void setRepository({Name}Repository repository) {
		this.repository = repository;
	}

}
