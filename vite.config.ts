import path from 'path';
import { defineConfig } from 'vite';
import Vue from '@vitejs/plugin-vue';
import Pages from 'vite-plugin-pages';
import Layouts from 'vite-plugin-vue-layouts';
import Icons from 'unplugin-icons/vite';
import IconsResolver from 'unplugin-icons/resolver';
import Components from 'unplugin-vue-components/vite';
import AutoImport from 'unplugin-auto-import/vite';

export default defineConfig({
	resolve: {
		alias: {
			'~/': `${path.resolve(__dirname, 'src')}/`,
			'~bulma': 'node_modules/bulma',
		},
	},
	plugins: [
		Vue(),
		AutoImport(),
		Pages(),
		Layouts(),
		Components({
			dts: true,
			resolvers: IconsResolver() as any,
		}),
		Icons({
			compiler: 'vue3',
			autoInstall: true,
		}),
	],
});
