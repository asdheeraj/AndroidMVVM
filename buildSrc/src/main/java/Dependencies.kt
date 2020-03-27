object Versions {
    const val kotlinLib = "1.3.21"
    const val kotlin_couroutines = "1.3.5"
    const val room = "2.2.5"
    const val minSDK = 23
    const val targetSDK = 28
    const val compileSDK = 28
    const val retrofit = "2.6.0"
    const val gson = "2.8.5"
    const val gsonConverter = "2.5.0"
    const val httpLogging = "3.12.0"
    const val koin = "2.0.1"
    const val koinArch = "0.9.1"
    const val leakCanary = "1.6.3"
    const val junit = "4.12"
    const val mockito = "2.8.47"
    const val appCompat = "1.0.2"
    const val materialComponents = "1.0.0"
    const val rxAndroidTest = "3.2.0"
    const val archComp = "2.2.0"
    const val archExt = "2.2.0"
    const val constraintLayout = "1.1.3"
    const val jodaTime = "2.10.1"
    const val navigationGraph= "2.1.0-alpha02"
    const val workManager = "2.1.0-rc01"
    const val powerMokito = "1.7.0RC4"
    const val places = "2.0.0"
    const val biometic = "1.0.0-beta02"
    const val recyclerview = "1.1.0"
    const val recyclerviewSelection = "1.1.0-beta01"
    const val picasso = "2.71828"
    const val mockk = "1.9.3"
    const val archCoreTesting = "2.1.0"
}

object LibDeps {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinLib}"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_couroutines}"
    val room = "androidx.room:room-ktx:${Versions.room}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomAnnotations = "androidx.room:room-compiler:${Versions.room}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    val httpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.httpLogging}"
    val koinCore = "org.koin:koin-core:${Versions.koin}"
    val koin = "org.koin:koin-android:${Versions.koin}"
    val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val junit = "junit:junit:${Versions.junit}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    val androidxTest = "androidx.test.espresso:espresso-core:${Versions.rxAndroidTest}"
    val koinTest = "org.koin:koin-test:${Versions.koin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.archComp}"
    val lifeCycleExtn = "androidx.lifecycle:lifecycle-extensions:${Versions.archExt}"
    val lifeCycleCommon = "androidx.lifecycle:lifecycle-common:${Versions.archComp}"
    val lifeCycleliveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.archComp}"
    val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"
    val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    val powerMockitoCore = "org.powermock:powermock-core:${Versions.powerMokito}"
    val powerMockitoModule = "org.powermock:powermock-module-junit4:${Versions.powerMokito}"
    val powerMockMokito = "org.powermock:powermock-api-mockito2:${Versions.powerMokito}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
}

object AppDeps {
    val koinAndroidArch = "org.koin:koin-android-architecture:${Versions.koinArch}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationGraph}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationGraph}"
    val places = "com.google.android.libraries.places:places:${Versions.places}"
    val biometric = "androidx.biometric:biometric:${Versions.biometic}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:${Versions.recyclerviewSelection}"
}