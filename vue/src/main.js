import Vue from 'vue'
import Axios from 'axios'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import Router from 'vue-router'
import router from './router'
import vuex from 'vuex'
import {store} from './store'

Vue.config.productionTip = false
Vue.prototype.$http = Axios
Vue.use(Router)

new Vue({
  vuetify,
  router,
  Axios,
  vuex,
  store,
  render: h => h(App)
}).$mount('#app')
