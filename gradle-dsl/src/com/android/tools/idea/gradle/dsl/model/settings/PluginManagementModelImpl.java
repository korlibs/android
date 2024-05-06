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
package com.android.tools.idea.gradle.dsl.model.settings;

import com.android.tools.idea.gradle.dsl.api.repositories.RepositoriesModel;
import com.android.tools.idea.gradle.dsl.api.settings.PluginManagementModel;
import com.android.tools.idea.gradle.dsl.api.settings.PluginsBlockModel;
import com.android.tools.idea.gradle.dsl.model.GradleDslBlockModel;
import com.android.tools.idea.gradle.dsl.model.repositories.RepositoriesModelImpl;
import com.android.tools.idea.gradle.dsl.parser.plugins.PluginsDslElement;
import com.android.tools.idea.gradle.dsl.parser.repositories.RepositoriesDslElement;
import com.android.tools.idea.gradle.dsl.parser.settings.PluginManagementDslElement;
import org.jetbrains.annotations.NotNull;

public class PluginManagementModelImpl extends GradleDslBlockModel implements PluginManagementModel {
  public PluginManagementModelImpl(@NotNull PluginManagementDslElement element) {
    super(element);
  }

  @Override
  public @NotNull PluginsBlockModel plugins() {
    PluginsDslElement pluginsDslElement = myDslElement.ensurePropertyElement(PluginsDslElement.PLUGINS);
    return new PluginsBlockModelImpl(pluginsDslElement);
  }

  @Override
  public @NotNull RepositoriesModel repositories() {
    RepositoriesDslElement repositoriesDslElement = myDslElement.ensurePropertyElement(RepositoriesDslElement.REPOSITORIES);
    return new RepositoriesModelImpl(repositoriesDslElement);
  }
}
