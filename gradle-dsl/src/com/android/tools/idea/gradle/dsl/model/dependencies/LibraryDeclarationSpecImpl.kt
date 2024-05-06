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
package com.android.tools.idea.gradle.dsl.model.dependencies

import com.android.tools.idea.gradle.dsl.api.dependencies.LibraryDeclarationSpec
import com.android.tools.idea.gradle.dsl.api.dependencies.VersionDeclarationSpec
import com.android.tools.idea.gradle.dsl.utils.GRADLE_PATH_SEPARATOR
import com.google.common.base.Joiner
import com.google.common.base.Splitter
import com.google.common.collect.Lists

class LibraryDeclarationSpecImpl(private var name: String,
                                 private var group: String,
                                 private var version: VersionDeclarationSpec?) : LibraryDeclarationSpec {


  companion object {
    fun create(notation: String): LibraryDeclarationSpecImpl? {
      // TOML dependency notation must have at least group and name
      // version is optional since it can be defined by BOM
      val segments = Splitter.on(GRADLE_PATH_SEPARATOR).trimResults().omitEmptyStrings().splitToList(notation)
      return when (segments.size) {
        2 -> {
          LibraryDeclarationSpecImpl(segments[1], segments[0], null)
        }
        3 -> {
          val versionSpec = VersionDeclarationSpecImpl.create(segments[2])
          LibraryDeclarationSpecImpl(segments[1], segments[0], versionSpec)
        }
        else -> null
      }
    }
  }

  override fun getName(): String = name

  override fun getGroup(): String = group

  override fun getVersion(): VersionDeclarationSpec? = version

  fun setName(newName: String) {
    name = newName
  }

  fun setGroup(newGroup: String) {
    group = newGroup
  }

  fun setVersion(newVersion: VersionDeclarationSpec?) {
    version = newVersion
  }

  fun setStringVersion(newVersion: String?){
    version = newVersion?.let { VersionDeclarationSpecImpl.create(it) }
  }

  override fun toString(): String = compactNotation()

  override fun compactNotation(): String {
    val versionString = version?.let {
      val str = it.compactNotation()
      if (str.isNullOrBlank()) null else str
    }
    return Joiner.on(GRADLE_PATH_SEPARATOR).skipNulls().join(
      Lists.newArrayList(group, name, versionString)
    )
  }
}
