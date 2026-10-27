package co.com.yourcompany.certification.nameproject.model.builders;

import co.com.yourcompany.certification.nameproject.exceptions.RepositoryModelCreationException;
import co.com.yourcompany.certification.nameproject.model.Repository;
import co.com.yourcompany.certification.nameproject.model.enumerables.GitIgnore;
import co.com.yourcompany.certification.nameproject.model.enumerables.License;
import co.com.yourcompany.certification.nameproject.util.builder.Builder;

public class RepositoryBuilder implements Builder<Repository> {

    private String name;
    private String description;
    private boolean initializeWithREADME;
    private GitIgnore gitIgnore;
    private License license;

    private RepositoryBuilder(String name) {
        this.name = name;
        this.initializeWithREADME = false;
        this.description = "";
        this.gitIgnore = GitIgnore.NONE;
        this.license = License.NONE;
    }

    public static RepositoryBuilder name(String name){
        return new RepositoryBuilder(name);
    }

    public RepositoryBuilder description(String description){
        this.description = description;
        return this;
    }

    public RepositoryBuilder initializeWithREADME(){
        this.initializeWithREADME = true;
        return this;
    }

    public RepositoryBuilder gitIgnore(GitIgnore gitIgnore){
        this.gitIgnore = gitIgnore;
        return this;
    }

    public RepositoryBuilder license(License license){
        this.license = license;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isInitializeWithREADME() {
        return initializeWithREADME;
    }

    public GitIgnore getGitIgnore() {
        return gitIgnore;
    }

    public License getLicense() {
        return license;
    }

    @Override
    public Repository build() throws RepositoryModelCreationException {
        return new Repository(this);
    }

}
