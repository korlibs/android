/*
 * Copyright (C) 2021 The Android Open Source Project
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
package com.android.tools.idea.gradle.dsl.parser.semantics;

import java.util.LinkedHashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExternalToModelMap {
  public static class Entry {
    @NotNull public final SurfaceSyntaxDescription surfaceSyntaxDescription;
    @NotNull public final ModelEffectDescription modelEffectDescription;
    @Nullable public final VersionConstraint versionConstraint;

    Entry(
      @NotNull SurfaceSyntaxDescription surfaceSyntaxDescription,
      @NotNull ModelEffectDescription modelEffectDescription,
      @Nullable VersionConstraint versionConstraint
    ) {
      this.surfaceSyntaxDescription = surfaceSyntaxDescription;
      this.modelEffectDescription = modelEffectDescription;
      this.versionConstraint = versionConstraint;
    }
  }

  @NotNull private final Set<Entry> entrySet;

  @NotNull public Set<Entry> getEntrySet() {
    return entrySet;
  }

  ExternalToModelMap(@NotNull Set<Entry> entrySet) {
    this.entrySet = entrySet;
  }

  public static final ExternalToModelMap empty = new ExternalToModelMap(new LinkedHashSet<>());
}