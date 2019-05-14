import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

function route (path, file, name, children) {
  return {
    exact: true,
    path,
    name,
    children,
    component: () => import('../pages' + file)
  }
}

export default new Router({
  routes: [
    route("/login",'/Login',"Login"),// /login路径，路由到登录组件
    {
      path:"/", // 根路径，路由到 Layout组件
      component: () => import('../pages/Layout'),
      redirect:"/index/dashboard",
      children:[
        route("/index/dashboard","/Dashboard","Dashboard"),

        route("/product/category",'/product/Category',"Category"),
        route("/product/runningmachine",'/product/RunningMachine',"RunningMachine"),
        route("/product/installer",'/product/Installer',"Installer"),
        // 其它所有组件都是 Layout的子组件

        route("/item/category",'/item/Category',"ItemCategory"),
        route("/item/brand",'/item/Brand',"Brand"),
        route("/item/list",'/item/Goods',"Goods"),
        route("/item/specification",'/item/specification/Specification',"Specification")
      ]
    }
  ]
})
