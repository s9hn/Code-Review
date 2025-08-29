plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":domain:book"))
    implementation(libs.coroutines.core)
    implementation(libs.paging.common)
}
