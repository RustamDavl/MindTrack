package ru.rstd.mtrack.test.unit.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.beans.factory.annotation.Autowired;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

@AnalyzeClasses(
        packages = "ru.rstd.mtrack",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class DependencyArchTest {

    @ArchTest
    static final ArchRule core_should_not_depend_on_web_or_boot =
            noClasses()
                    .that()
                    .resideInAPackage("..core..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..application.."
                    );

    @ArchTest
    static final ArchRule core_impl_should_depend_on_core_api =
            classes()
                    .that()
                    .resideInAPackage("..core.service.impl..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..core.service.api.."
                    );

    @ArchTest
    static final ArchRule core_impl_should_depend_on_core_api_v2 =
            classes()
                    .that()
                    .resideInAPackage("..core.repository..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..entity..",
                            "..core.dao.api.."
                    );

    @ArchTest
    static final ArchRule services_should_not_depend_on_controllers =
            noClasses()
                    .that()
                    .resideInAnyPackage("..service..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..rest..");

    @ArchTest
    static final ArchRule controllers_should_not_depend_on_repositories =
            noClasses()
                    .that()
                    .resideInAnyPackage("..rest..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..repository..",
                            "..dao.."
                    );

    @ArchTest
    static final ArchRule no_field_injection =
            noFields()
                    .should()
                    .beAnnotatedWith(Autowired.class);
}
