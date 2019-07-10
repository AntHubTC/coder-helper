<!--
  author: tangcheng_cd
  create date: 2019/07/10
  description: 管理首页
-->
<template>
    <div class="dt-system-manager">
        <v-navigate-page ref="navigator" :pageConfig="pageConfig">
            <dt-system-main-page slot="mainPage" @navigate="navigateTo"/>
            <dt-system-add-modify slot="addModifyPage" @navigate="navigateTo"/>
        </v-navigate-page>
    </div>
</template>

<script type="text/ecmascript-6">
    import dtSystemMainPage from './dtSystemMainPage'
    import dtSystemAddModify from './dtSystemAddModify'

    export default {
        name: 'dt-system-manager',
        components: {
            dtSystemMainPage,
            dtSystemAddModify
        },
        data () {
            return {
                pageConfig: {
                    active: 'mainPage',
                    pages: ['mainPage', 'addModifyPage']
                }
            }
        },
        methods: {
            // 前往某一页
            navigateTo (page) {
                this.$refs.navigator.navigate(page)
            }
        }
    }
</script>

<style lang="stylus" rel="stylesheet/stylus">
.dt-system-manager
    height 100%
    width 100%
</style>
