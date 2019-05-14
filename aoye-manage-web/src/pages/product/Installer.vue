<template>
  <v-card>
    <v-card-title>
      <v-btn color="primary" @click="addInstaller">新增客户端</v-btn>
      <!--<v-btn color="primary" @click="test">测试一下</v-btn>-->
      <!--搜索框，与search属性关联-->
      <v-spacer/>
      <v-flex xs3>
        <v-text-field label="输入版本名称关键字搜索" v-model.lazy="search" append-icon="search" hide-details/>
      </v-flex>
    </v-card-title>
    <v-divider/>
    <v-data-table
      :headers="headers"
      :items="installers"
      :pagination.sync="pagination"
      :total-items="totalInstallers"
      :loading="loading"
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.cname }}</td>
        <td class="text-xs-center">{{ props.item.cid }}</td>
        <td class="text-xs-center">{{ props.item.versionCode }}</td>
        <td class="text-xs-center">{{ props.item.versionNum }}</td>
        <td class="text-xs-center">{{ props.item.fileName }}</td>
        <td class="text-xs-center">{{ props.item.fileSize }}</td>

        <td class="justify-center layout px-0">

          <v-btn icon @click="downloadInstaller(props.item)">
            <i class="el-icon-caret-bottom"/>
          </v-btn>

          <v-btn icon @click="editInstaller(props.item)">
            <i class="el-icon-edit"/>
          </v-btn>
          <v-btn icon @click="deleteInstaller(props.item)">
            <i class="el-icon-delete"/>
          </v-btn>
        </td>
      </template>
    </v-data-table>
    <!--弹出新增对话框-->
    <v-dialog max-width="500" v-model="Show" persistent scrollable>
      <v-card>
        <!--对话框的标题-->
        <v-toolbar dense dark color="primary">
          <v-toolbar-title>新增客户端</v-toolbar-title>
          <v-spacer/>
          <!--关闭窗口的按钮-->
          <v-btn icon @click="closeWindow"><v-icon>close</v-icon></v-btn>
        </v-toolbar>
        <!--对话框的内容，表单-->
        <v-card-text class="px-5" style="height:400px">
          <installer-form @close="closeWindow" :oldInstaller="oldInstaller" :isEdit="isEdit" ref="installerForm"/>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
  // 导入自定义的表单组件
  import InstallerForm from './InstallerForm'
  export default {
    name: "Installer",
    data() {
      return {
        search: '', // 搜索过滤字段
        totalInstallers: 0, // 总条数
        installers: [], // 当前页品牌数据
        loading: true, // 是否在加载中
        pagination: {}, // 分页信息
        headers: [
          {text: '客户端（可排序,单位为时间毫秒秘钥）', align: 'center', value: 'id'},
          {text: '所属分类', align: 'center', sortable: false, value: 'cname'},
          {text: '所属分类id', align: 'center', sortable: false, value: 'cid'},
          {text: '版本名称', align: 'center', sortable: false, value: 'versionCode'},
          {text: '版本序号', align: 'center', sortable: false, value: 'versionNum'},
          {text: '文件名称', align: 'center', sortable: false, value: 'fileName'},
          {text: '文件大小', align: 'center', sortable: false, value: 'fileSize'},
          {text: '操作', align: 'center', value: 'id', sortable: false}
        ],
        Show: false,// 控制对话框的显示
        oldInstaller: {}, // 即将被编辑的品牌数据
        isEdit: false, // 是否是编辑
        flag:1
      }
    },
    mounted() { // 渲染后执行
      // 查询数据
      this.getDataFromServer();
    },
    watch: {
      pagination: { // 监视pagination属性的变化
        deep: true, // deep为true，会监视pagination的属性及属性中的对象属性变化
        handler() {
          // 变化后的回调函数，这里我们再次调用getDataFromServer即可
          this.getDataFromServer();
        }
      },
      search: { // 监视搜索字段
        handler() {
          this.getDataFromServer();
        }
      }
    },
    methods: {
      getDataFromServer() { // 从服务的加载数的方法。
        // 发起请求
        this.$http.get("/product/installer/page", {
          params: {
            key: this.search, // 搜索条件
            page: this.pagination.page,// 当前页
            rows: this.pagination.rowsPerPage,// 每页大小
            sortBy: this.pagination.sortBy,// 排序字段
            desc: this.pagination.descending// 是否降序
          }
        }).then(resp => { // 这里使用箭头函数
          this.installers = resp.data.items;
          this.totalInstallers = resp.data.total;
          // 完成赋值后，把加载状态赋值为false
          this.loading = false;
        })
      },

      addInstaller() {
        // 修改标记
        this.isEdit = false;
        // 控制弹窗可见：
        this.Show = true;
        // 把oldInstaller变为null
        this.oldInstaller = null;
      },

      /*test(){
        if (this.flag % 2 == 0) {
          window.location.href='http://www.baidu.com';
        }else {
          alert("我是奇数！")
        }
        this.flag++;
        //this.$router.push("/product/installer");
      },*/
      downloadInstaller(oldInstaller){
        //this.$http.get("/product/installer/download?id=" + oldInstaller.id);
        //this.$http.get("http://192.168.0.118:8081/installer/download?id=" + oldInstaller.id);
        //window.open("/product/installer/download?id=" + oldInstaller.id, '_blank');
        window.location.href = "http://192.168.0.118:10010/api/product/installer/download?id=" + oldInstaller.id;
      },
      editInstaller(oldInstaller){
        // 根据品牌信息查询商品分类
        this.$http.get("/product/installer?id=" + oldInstaller.id)
          .then(({data}) => {
            // 修改标记
            this.isEdit = true;
            // 控制弹窗可见：
            this.Show = true;
            // 获取要编辑的installer
            this.oldInstaller = oldInstaller;
          })
      },

      deleteInstaller(oldInstaller){
        // 根据品牌信息查询商品分类
        this.$http.delete("/product/installer/?id=" + oldInstaller.id)
          .then(() => {
            this.$message.success("删除成功！");
            this.getDataFromServer();
          })
      },
      closeWindow(){
        // 重新加载数据
        this.getDataFromServer();
        //重置子表单
        this.$refs.installerForm.clear();
        // 关闭窗口
        this.Show = false;
      }
    },
    components:{
      InstallerForm
    }
  }
</script>

<style scoped>

</style>
