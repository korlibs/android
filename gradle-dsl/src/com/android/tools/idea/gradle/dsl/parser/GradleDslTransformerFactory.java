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
package com.android.tools.idea.gradle.dsl.parser;

import com.android.tools.idea.gradle.dsl.model.BuildModelContext;
import com.android.tools.idea.gradle.dsl.parser.files.GradleDslFile;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Extension point to provide parser/writer pair for Gradle build files (e.g. for different languages).
 *
 * This parser/writer factory will be used to manipulate build file to provide content-related features,
 * e.g. {@link com.intellij.externalSystem.ExternalDependencyModificator}. It does not affect code navigation/highlighting
 */
@ApiStatus.Internal
public interface GradleDslTransformerFactory {
  ExtensionPointName<GradleDslTransformerFactory> EXTENSION_POINT_NAME =
    ExtensionPointName.create("com.android.tools.idea.gradle.dsl.transformerFactory");

  boolean canTransform(@NotNull PsiFile psiFile);

  @NotNull
  GradleDslParser createParser(@NotNull PsiFile psiFile, @NotNull BuildModelContext context, @NotNull GradleDslFile dslFile);

  @NotNull
  GradleDslWriter createWriter(@NotNull BuildModelContext context);
}
