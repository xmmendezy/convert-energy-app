import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import { setupLayouts } from 'virtual:generated-layouts';
import generatedRoutes from 'virtual:generated-pages';
import MainApp from './main.vue';
import './scss/all.scss';

const app = createApp(MainApp);

app.use(
	createRouter({
		routes: setupLayouts(generatedRoutes),
		history: createWebHistory(),
	}),
);

app.mount('#app');
