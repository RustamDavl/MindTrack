package ru.rstd.mtrack.test.unit.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(
        packages = "ru.rstd.mtrack",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class ModuleDependencyArchTest {

    @ArchTest
    static final ArchRule core_should_not_depend_on_application =
            noClasses()
                    .that()
                    .resideInAPackage("..core..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..application..");

    @ArchTest
    static final ArchRule core_should_not_depend_on_test =
            noClasses()
                    .that()
                    .resideInAPackage("..core..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..test..");

    @ArchTest
    static final ArchRule application_should_not_depend_on_test =
            noClasses()
                    .that()
                    .resideInAPackage("..application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..test..");

    @ArchTest
    static final ArchRule modules_should_not_have_cycles =
            slices()
                    .matching("ru.rstd.mtrack.(*)..")
                    .should()
                    .beFreeOfCycles();
}