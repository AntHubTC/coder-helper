<!--
  author: tangcheng_cd
  create date: 2019/07/10
  description: 管理首页
-->
<template>
    <div class="dt-system-main-page">
        <!--主页面头-->
        <v-header title="迁移系统管理"></v-header>
        <!--主页面内容-->
        <el-container>
            <el-header style="height: 50px">
                <el-row class="toolbar">
                    <el-button type="primary" size="mini" icon="el-icon-plus" @click="addBtnClick">增加</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-delete" @click="deleteData">删除</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="editBtnClick">编辑</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-refresh" @click="refreshBtnClicked">刷新</el-button>
                    <div class="search-box">
                        <el-input
                                placeholder="输入系统编号或系统名称搜索"
                                auto-complete="on"
                                size="mini"
                                v-model="searchText"
                                @keydown.enter.native="refreshData"
                                clearable>
                            <el-button type="primary"
                                       slot="append"
                                       @click="refreshData"
                                       icon="el-icon-search"></el-button>
                        </el-input>
                    </div>
                </el-row>
            </el-header>
            <el-main>
                <dt-system-grid ref="dataGrid" @rowDblClick="eidtGridDataRow"/>
            </el-main>
        </el-container>
    </div>
</template>

<script type="text/ecmascript-6">
    import dtSystemGrid from './dtSystemGrid'
    import Msg from '@asst/js/msg'
    import {
        DELETE_PARAM_INFO
    } from '@api/url'
    import ajax from '@api/ajax'
    import { createNamespacedHelpers } from 'vuex'
    const { mapMutations } = createNamespacedHelpers('metaManager/param')

    export default {
        name: 'dt-system-main-page',
        components: {
            dtSystemGrid
        },
        data () {
            return {
                searchText: ''
            }
        },
        methods: {
            ...mapMutations({
                setPageMode: 'setPageMode',
                setCurrentEditItem: 'setCurrentEditItem'
            }),
            addBtnClick () {
                this.setPageMode('ADD')
                this.setCurrentEditItem({})
                this.$emit('navigate', 'addModifyPage')
            },
            eidtGridDataRow (selectedData) {
                this.setPageMode('MODIFY')
                this.setCurrentEditItem(selectedData)
                this.$emit('navigate', 'addModifyPage')
            },
            editBtnClick () {
                let dataGrid = this.$refs.dataGrid
                let selectedData = dataGrid.getCurrentRow()
                if (!selectedData) {
                    this.$message.error('请至少选择一条记录!')
                } else {
                    this.eidtGridDataRow(selectedData)
                }
            },
            deleteData () {
                let dataGrid = this.$refs.dataGrid
                let selectedData = dataGrid.getCurrentRow()
                if (!selectedData) {
                    this.$message.error('请至少选择一条记录!')
                } else {
                    Msg.confirm('是否删除所选记录？', {
                        onConfirm: () => {
                            ajax.post(DELETE_PARAM_INFO, selectedData).then(res => {
                                this.$message({
                                    type: 'success',
                                    message: '删除成功!'
                                })
                                dataGrid.clearSelection()
                                this.refreshData()
                            }).catch(err => {
                                console.error('删除数据发生错误！', err)
                            })
                        },
                        onCancel () {
                        }
                    })
                }
            },
            refreshBtnClicked () {
                this.refreshData()
                this.$message({
                    type: 'success',
                    message: '刷新成功!'
                })
            },
            refreshData () {
                let dataGrid = this.$refs.dataGrid
                dataGrid.getDataByKeyword(this.searchText)
                dataGrid.clearSelection()
            }
        }
    }
</script>

<style lang="stylus" rel="stylesheet/stylus">
    .dt-system-main-page
        height 100%
        font-size 14px
        .el-container
            padding: 10px
            height 100%
            .el-main
                height calc(100% - 55px - 20px)
                padding 0 0
                margin-bottom 20px
                .dt-system-grid
                    height 100%
                    &>div.el-table
                        height calc(100% - 50px)
                        &>div.el-table__body-wrapper
                            height calc(100% - 49px)
                            overflow-y auto
                    .el-pagination
                        background-color #FFFFFF
        .el-header
            padding 0px
            margin-bottom 5px
            .toolbar
                height 50px
                line-height 50px
                vertical-align middle
                background-color #fff
                padding 2px 15px
                .search-box
                    display inline-block
                    margin-left 5px
                    float right
                    .el-input
                        width 300px
</style>
