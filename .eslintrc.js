module.exports = {
	root: true,
	env: {
		browser: true,
		node: true,
	},
	parser: 'vue-eslint-parser',
	extends: [
		'plugin:vue/vue3-essential',
		'plugin:@typescript-eslint/recommended',
		'prettier',
		'plugin:prettier/recommended',
	],
	parserOptions: {
		parser: '@typescript-eslint/parser',
		ecmaVersion: 2021,
		sourceType: 'module',
		ecmaFeatures: {
			jsx: true,
		},
	},
	plugins: [],
	rules: {
		'no-console': 'off',
		'no-unused-vars': 'off',
		'@typescript-eslint/no-unused-vars': 'off',
		'vue/multi-word-component-names': 'off',
	},
};
