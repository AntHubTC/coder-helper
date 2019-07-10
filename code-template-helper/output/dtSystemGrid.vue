<!--
  author: tangcheng_cd
  create date: 2019/07/10
  description: 系统数据表格
-->
<template>
    <div class="dt-system-grid">
        <el-table
                :data="pageData.list"
                @row-dblclick="rowDblClicked"
                highlight-current-row
                v-loading="tableLoading"
                @row-click="rowClicked">
            <!-- 扩展部分 -->
            <slot name="extendStartSlot"></slot>
            <el-table-column
                    prop="sysNo"
                    label="系统编号"
                    width="200"
            >
            </el-table-column>
            <el-table-column
                    prop="sysType"
                    label="系统类型"
                    width="200"
            >
            </el-table-column>
            <el-table-column
                    prop="sysName"
                    label="系统名称"
                    width="200"
            >
            </el-table-column>
            <!-- 扩展部分，例如操作按钮项 -->
            <slot name="extendEndSlot"></slot>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="search.pageNo"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="search.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="pageData.total">
        </el-pagination>
    </div>
</template>

<script type="text/ecmascript-6">
    import {
        SEARCH_PARAM_BY_EXAMPLE
    } from '@api/url'
    import ajax from '@api/ajax'

    export default {
        name: 'dt-system-grid',
        data () {
            return {
                pageData: {
                    list: [],
                    total: 0
                }, // 表格数据
                search: {
                    pageNo: 1,
                    pageSize: 10,
                    keyword: null
                },
                tableLoading: false, // 数据是否加载中
                currentRow: null // 表格中选择的数据
            }
        },
        methods: {
            handleSizeChange (pageSize) {
                this.search.pageNo = 1
                this.search.pageSize = pageSize
                this.getAllData()
            },
            handleCurrentChange (pageNo) {
                this.search.pageNo = pageNo
                this.getAllData()
            },
            getDataByKeyword (keyword) {
                this.search.keyword = keyword
                this.getAllData()
            },
            // 获取所有的数据
            getAllData () {
                // 数据加载中
                this.tableLoading = true
                ajax.post(SEARCH_PARAM_BY_EXAMPLE, this.search).then(res => {
                    this.pageData = res.data
                    // 数据加载完毕
                    this.tableLoading = false
                }).catch(e => {
                    console.error('获取数据发生错误！')
                })
            },
            // 获取所有的选择记录
            getCurrentRow () {
                return this.currentRow
            },
            // 处理数据表格选择
            rowClicked (row, el) {
                this.currentRow = row
            },
            // 清除选择项数据
            clearSelection () {
                this.currentRow = null
            },
            // 双击编辑
            rowDblClicked (row, el) {
                this.$emit('rowDblClick', row)
            }
        },
        created () {
            // 获取所有的数据
            this.getAllData()
            this.currentRow = null
        }
    }
</script>

<style lang="stylus" rel="stylesheet/stylus">

</style>
