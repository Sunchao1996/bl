package com.bl.core.tagrules;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * 
 * what:  css脚本标签
 *
 */
public class CssTagRuleBundle implements TagRuleBundle {
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("bl-css",
                new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("bl-css"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}
