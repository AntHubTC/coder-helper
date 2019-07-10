package com.tc.codehelper.vue.elementUI.form.component;

public class ElInput extends ElFormInputComponent {

    public ElInput() {
        super("input");
    }

    public ElInput(String name) {
        super("input", name);
    }

    public ElInput(String name, String placeholder) {
        super("input", name, placeholder);
    }
}
