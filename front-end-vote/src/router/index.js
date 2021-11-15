import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import CreatePoll from "@/views/CreatePoll";
import ViewPoll from "@/views/ViewPoll";
import { authGuard } from '../auth/authGuard';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Dashboard.vue'),
    beforeEnter: authGuard,
  },
  {
    path: '/create',
    name: 'CreatePoll',
    component: CreatePoll,
    beforeEnter: authGuard,
  },
  {
    path:'/poll/:id',
    component: ViewPoll,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
