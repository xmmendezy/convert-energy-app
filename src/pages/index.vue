<template>
	<div class="content">
		<p class="title-index is-size-4 mb-4">Conversor Energético</p>
		<div class="card">
			<div class="card-content">
				<router-link class="button has-text-left mb-5" :class="[size_text]" to="/convert_units">
					<fas-random class="icon translate" />
					<span class="ml-4"> Convertir Unidades </span>
				</router-link>

				<router-link class="button has-text-left mb-5" :class="[size_text]" to="/convert_energy">
					<fas-gas-pump class="icon translate" />
					<span class="ml-4"> Equivalente Energético </span>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const size_text = ref('is-size-7');

if (window.innerHeight < 570) {
	size_text.value = 'is-size-7';
} else {
	size_text.value = 'is-size-6';
}
</script>

<style lang="scss">
@import '../scss/vars.scss';

.content {
	margin: auto;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 80vw;

	.title-index {
		color: $primary;
	}

	.card {
		height: auto;

		.button {
			width: 70vw;
			height: 7.5vh;
			background-color: $primary;
			color: #fff;
		}
	}
}
</style>
