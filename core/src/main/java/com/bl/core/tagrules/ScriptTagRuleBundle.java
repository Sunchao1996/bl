package com.bl.core.tagrules;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * 
 * what: javascript脚本标签
 *
 */
public class ScriptTagRuleBundle implements TagRuleBundle {
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("bl-script",
                new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("bl-script"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}
