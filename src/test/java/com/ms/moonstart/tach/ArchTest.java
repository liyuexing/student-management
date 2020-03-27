package com.ms.moonstart.tach;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ms.moonstart.tach");

        noClasses()
            .that()
                .resideInAnyPackage("com.ms.moonstart.tach.service..")
            .or()
                .resideInAnyPackage("com.ms.moonstart.tach.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.ms.moonstart.tach.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
