/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.tools.idea.gradle.dsl.api.dependencies

/**
 * Holding data for library dependency declaration for version catalog
 * This is model for TOML representation of dependency declaration
 * Name and Group are required attributes, Version is not
 */
interface LibraryDeclarationSpec {

  fun getName(): String

  fun getGroup(): String

  fun getVersion(): VersionDeclarationSpec?

  override fun toString(): String

  fun compactNotation(): String
}