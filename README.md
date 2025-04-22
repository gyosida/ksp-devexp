# Talk: Boost your developer experience with Kotlin Symbol Processing (KSP)
This repository contains the source code used in the live coding session of my talk at [Meetup # 66 from Android Dev Peru](https://www.linkedin.com/posts/android-dev-peru_android-kotlin-androiddevperu-activity-7320130683085983744-JSdr?utm_source=share&utm_medium=member_desktop&rcm=ACoAAAxgU7IBXxhRycLflF2J6qD0-xHw0CXzjfw)

## Modules

### before-ksp
Implements the feature flag by hand creating a bunch of boilerplate code.

### after-ksp
Implements the feature flag using annotations and leveraging KSP for code generation.

### ksp-processors
Implements the feature flag code generation using the KSP API.

### ksp-processors-incomplete
The module used in the live coding session to fill the missing pieces of code to have a working KSP processor.

### feature-flags-api
Contains the feature flags API and annotations.
