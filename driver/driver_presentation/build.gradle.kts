apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.driverDomain))
   // "implementation"(Google.mat_icons)

    "implementation"(Coil.coilCompose)
}