<!--
  author: tangcheng_cd
  create date: 2019/07/10
  description: 系统新增删除
-->
<template>
    <div class="dt-system-add-modify">
        <v-header :title="pageMode === 'ADD' ? '新增系统' : '编辑系统'" >
            <template slot="left" slot-scope="{ title }">
                <p class="title lf">
                    <span title="返回" @click="$emit('navigate', 'mainPage')" class="iconfont icon-back back-btn"/>
                    {{title}}
                </p>
            </template>
        </v-header>
        <div class="dt-system-add-modify-content">
            <el-form
                    ref="form"
                    :model="formData"
                    :rules="formRules"
                    size="mini"
                    label-width="90px"
                    label-position="right">
                    <el-form-item label="系统编号" prop="sysNo">
                        <el-input v-model="formData.sysNo"
                                  placeholder="请在此输入">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="系统类型" prop="sysType">
                        <el-input v-model="formData.sysType"
                                  placeholder="请在此输入">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="系统名称" prop="sysName">
                        <el-input v-model="formData.sysName"
                                  placeholder="请在此输入">
                        </el-input>
                    </el-form-item>
            </el-form>
            <div class="form-button-box">
                <el-button @click="resetForm()" size="small" class="dialog-btn">重置</el-button>
                <el-button @click="submitFormData" type="primary" size="small" class="dialog-btn">保存</el-button>
            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">
    import {
        ADD_PARAM_INFO,
        MODIFY_PARAM_INFO
    } from '@api/url'
    import ajax from '@api/ajax'
    import {
        numLetteValidators
    } from '@asst/js/rules'
    import { createNamespacedHelpers } from 'vuex'
    const { mapState } = createNamespacedHelpers('common/generalCURD')

    export default {
        name: 'dt-system-add-modify',
        data () {
            return {
                pageMode: {
                    type: String,
                    default: 'ADD'
                },
                currentItem: {
                },
                formData: {},
                formRules: {
                    sysNo: [
                        {"required":true,"message":"此项为必输项","trigger":"blur"},
                        {"min":1,"max":4,"message":"长度在 1 到 4 之间","trigger":"blur"},
                        {"validator":"numLetteValidators","message":"","trigger":"blur"}
                    ],
                    sysType: [
                        {"required":true,"message":"此项为必输项","trigger":"blur"}
                    ],
                    sysName: [
                        {"min":1,"max":66,"message":"长度在 1 到 66 之间","trigger":"blur"}
                    ]
                }
            }
        },
        computed: {
            ...mapState({
                pageModeInStore: 'pageMode',
                currentEditItemInStore: 'currentEditItem'
            })
        },
        methods: {
            addShow () {
                this.pageMode = 'ADD'

                this.currentItem = {}
                this.formData = {...this.currentItem}
            },
            editShow (selUserNode) {
                this.pageMode = 'MODIFY'

                this.currentItem = selUserNode
                this.formData = {...this.currentItem}
            },
            ardCancelBtnClick () {
                this.formData = {}
                this.$refs.form.clearValidate()
            },
            resetForm () {
                this.formData = {...this.currentItem}
                this.$refs.form.clearValidate()
            },
            preHandFormData (formData) {
                return formData
            },
            add (postData, callback) {
                ajax.post(ADD_PARAM_INFO, postData).then(res => {
                    callback && callback()
                }).catch(e => {
                    console.error('新增系统参数发生错误！')
                })
            },
            update  (postData, callback) {
                ajax.post(MODIFY_PARAM_INFO, postData).then(res => {
                    callback && callback()
                }).catch(e => {
                    console.error('修改系统参数发生错误！')
                })
            },
            submitFormData () {
                this.$refs.form.validate((valid) => {
                if (valid) {
                    let postData = this.preHandFormData(this.formData)
                    let callback = () => {
                        this.$message({
                            type: 'success',
                            message: '保存成功!'
                        })
                        this.$emit('navigate', 'mainPage')
                    }
                    if (this.pageMode === 'ADD') {
                        this.add(postData, callback)
                    } else {
                        this.update(postData, callback)
                    }
                    } else {
                        return false
                    }
                })
            }
        },
        created () {
            // this.formData = JSON.parse(JSON.stringify(this.currentItem))
            if (this.pageModeInStore === 'ADD') {
                this.addShow()
            } else {
                this.editShow(this.currentEditItemInStore)
            }
        }
    }
</script>

<style lang="stylus" rel="stylesheet/stylus">
    .dt-system-add-modify
        .v-header
            .back-btn
                margin-right 5px
                cursor pointer
            &:hover
                color #ddd
            .dt-system-add-modify-content
                width 500px
                padding 20px
                .form-button-box
                    text-align right
                .el-form
                .el-date-editor
                    width 260px !important
    .el-select
        width 100%
</style>
