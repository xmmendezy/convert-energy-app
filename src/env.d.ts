/// <reference types="vite/client" />

declare module '*.vue' {
	import { DefineComponent } from 'vue';
	// eslint-disable-next-line @typescript-eslint/no-explicit-any, @typescript-eslint/ban-types
	const component: DefineComponent<{}, {}, any>;
	export default component;
}

declare module 'pages-generated' {
	import type { RouteRecordRaw } from 'vue-router';
	const routes: RouteRecordRaw[];
	export default routes;
}

declare module 'virtual:generated-pages' {
	import type { RouteRecordRaw } from 'vue-router';
	const routes: RouteRecordRaw[];
	export default routes;
}
