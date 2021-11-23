<template>
	<div class="tutorial">
		<div class="tutorial-top">
			<figure class="image">
				<img class="phone" ref="phone" src="../assets/welcome_phone.png" />
				<div class="is-overlay">
					<img class="logo" src="../assets/logo.png" />
				</div>
			</figure>
		</div>
		<div class="tutorial-content m-5">
			<p class="tutorial-title is-size-5">¡Bienvenido al Convertidor Energético!</p>
			<p class="has-text-centered mt-3" :class="[size_text]">
				Esta heramienta le permite: 1) Calcular unidades de energía, volumen o peso y 2) Calcular el equivalente
				enegético de cualquier producto al del sustituto que quieras convertir
			</p>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from 'vue';
import { templateRef, useTimeoutFn } from '@vueuse/core';

const phone = templateRef<HTMLImageElement>('phone');
const width = ref('0');

watchEffect(
	() => {
		if (phone.value) {
			useTimeoutFn(() => {
				width.value = `${phone.value.width}px`;
			}, 5);
		}
	},
	{ flush: 'post' },
);

const size_text = ref('is-size-8');

if (window.innerHeight < 570) {
	size_text.value = 'is-size-8';
} else if (window.innerHeight < 670) {
	size_text.value = 'is-size-7';
} else if (window.innerHeight < 750) {
	size_text.value = 'is-size-6';
} else {
	size_text.value = 'is-size-6';
}
</script>

<style lang="scss" scoped>
.tutorial {
	.phone {
		height: auto;
		width: auto;
	}

	.logo {
		width: calc(v-bind(width) - 2rem);
	}
}
</style>
