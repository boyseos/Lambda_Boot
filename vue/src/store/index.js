import Vue from 'vue'
import Vuex from 'vuex'
import common from '@/store/modules/common'
import person from '@/store/modules/person'
import article from '@/store/modules/article'

Vue.use(Vuex)

export const store = new Vuex.Store({
  modules:{
    common,
    person,
    article
  },
  strict : true
})