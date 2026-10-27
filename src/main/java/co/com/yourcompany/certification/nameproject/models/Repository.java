package co.com.yourcompany.certification.nameproject.model;

import co.com.yourcompany.certification.nameproject.exceptions.RepositoryModelCreationException;
import co.com.yourcompany.certification.nameproject.model.builders.RepositoryBuilder;
import co.com.yourcompany.certification.nameproject.model.enumerables.GitIgnore;
import co.com.yourcompany.certification.nameproject.model.enumerables.License;

import static co.com.yourcompany.certification.nameproject.util.validations.Validations.isEmptyOrNull;

public class Repository {

    private final String name;
    private final String description;
    private final boolean initializeWithREADME;
    private final GitIgnore gitIgnore;
    private final License license;

    public Repository(RepositoryBuilder builder) throws RepositoryModelCreationException {
        name = builder.getName();
        description = builder.getDescription();
        initializeWithREADME = builder.isInitializeWithREADME();
        gitIgnore = builder.getGitIgnore();
        license = builder.getLicense();
        isValid();
    }

    private void isValid() throws RepositoryModelCreationException {
        if (isEmptyOrNull(this.name)) {
            throw new RepositoryModelCreationException("The repository does not contain a name");
        }
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public boolean isInitializeWithREADME() {
        return initializeWithREADME;
    }

    public GitIgnore gitIgnore() {
        return gitIgnore;
    }

    public License license() {
        return license;
    }
}
