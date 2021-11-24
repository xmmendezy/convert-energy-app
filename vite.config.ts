import path from 'path';
import { defineConfig } from 'vite';
import Vue from '@vitejs/plugin-vue';
import Pages from 'vite-plugin-pages';
import Layouts from 'vite-plugin-vue-layouts';
import Icons from 'unplugin-icons/vite';
import IconsResolver from 'unplugin-icons/resolver';
import Components from 'unplugin-vue-components/vite';

export default defineConfig({
	resolve: {
		alias: {
			'~/': `${path.resolve(__dirname, 'src')}/`,
			'~bulma': 'node_modules/bulma',
			'~@fontsource': 'node_modules/@fontsource',
			'~vue3-carousel': 'node_modules/vue3-carousel',
		},
	},
	plugins: [
		Vue(),
		Pages(),
		Layouts(),
		Components({
			dts: true,
			resolvers: IconsResolver({
				prefix: false,
				alias: {
					fas: 'fa-solid',
				},
				// eslint-disable-next-line @typescript-eslint/no-explicit-any
			}) as any,
		}),
		Icons({
			compiler: 'vue3',
			autoInstall: true,
		}),
	],
});