<template>
	<div class="convert">
		<p class="title-index is-size-6 mt-4 mb-4">Equivalente Energético</p>
		<div class="card mb-2">
			<div class="card-content">
				<div class="card-elements is-inline-flex is-fullwidth">
					<div class="circle">
						<input type="number" class="circle-center" placeholder="Cantidad" v-model="cant" />
					</div>
					<div class="button-center">
						<button class="button mb-4" :class="[fuel_1[0] !== 1 ? 'active' : '']" @click="toggleModal1">
							{{ fuel_1[1] }}
						</button>
						<button
							class="button"
							:class="[unit_1[0] !== units_1[0][0] ? 'active' : '']"
							@click="toggleModal2"
						>
							{{ unit_1[1] }}
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-content">
				<p class="has-text-left is-size-7 mt-4 mb-4">Resultado - Equivalente Energético</p>
				<div class="card-elements is-inline-flex is-fullwidth">
					<div class="circle">
						<p class="circle-center">{{ typeof result === 'number' ? result : '---' }}</p>
						<p class="circle-center help">{{ typeof simbolo === 'string' ? simbolo : '' }}</p>
					</div>
					<div class="button-center">
						<button class="button mb-4" :class="[fuel_2[0] !== 1 ? 'active' : '']" @click="toggleModal3">
							{{ fuel_2[1] }}
						</button>
						<button
							class="button mb-4"
							:class="[unit_2[0] !== units_2[0][0] ? 'active' : '']"
							@click="toggleModal4"
						>
							{{ unit_2[1] }}
						</button>
					</div>
				</div>
				<div class="button-center is-fullwidth">
					<button
						v-if="typeof result === 'number'"
						class="button primary mt-4 mb-4 is-fullwidth"
						@click="clear"
					>
						Borrar
					</button>
					<button
						v-else
						class="button primary mt-4 mb-4 is-fullwidth"
						:disabled="!validForm"
						@click="convert"
					>
						Convertir
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" :class="[{ 'is-active': active_modal_1 }]">
		<div class="modal-background"></div>
		<div class="modal-card modal-card-select">
			<section class="modal-card-body">
				<ul>
					<li v-for="(f, i) in fuels" :key="i" class="has-text-left mt-2 mb-2" @click="selectModal1(i)">
						{{ f[1] }}
					</li>
				</ul>
			</section>
		</div>
	</div>
	<div class="modal" :class="[{ 'is-active': active_modal_2 }]">
		<div class="modal-background"></div>
		<div class="modal-card modal-card-select">
			<section class="modal-card-body">
				<ul>
					<li v-for="(u, i) in units_1" :key="i" class="has-text-left mt-2 mb-2" @click="selectModal2(i)">
						{{ u[1] }}
					</li>
				</ul>
			</section>
		</div>
	</div>
	<div class="modal" :class="[{ 'is-active': active_modal_3 }]">
		<div class="modal-background"></div>
		<div class="modal-card modal-card-select">
			<section class="modal-card-body">
				<ul>
					<li v-for="(f, i) in fuels" :key="i" class="has-text-left mt-2 mb-2" @click="selectModal3(i)">
						{{ f[1] }}
					</li>
				</ul>
			</section>
		</div>
	</div>
	<div class="modal" :class="[{ 'is-active': active_modal_4 }]">
		<div class="modal-background"></div>
		<div class="modal-card modal-card-select">
			<section class="modal-card-body">
				<ul>
					<li v-for="(u, i) in units_2" :key="i" class="has-text-left mt-2 mb-2" @click="selectModal4(i)">
						{{ u[1] }}
					</li>
				</ul>
			</section>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { useStore, Fuel } from '../store';

const store = useStore();

const active_modal_1 = ref(false);
const active_modal_2 = ref(false);
const active_modal_3 = ref(false);
const active_modal_4 = ref(false);

const fuels = store.getFuels();
const units_1 = ref<Fuel[]>([[1, 'Producto', 5]]);
const units_2 = ref<Fuel[]>([[1, 'Producto', 5]]);

const cant = ref<number | undefined>(undefined);
const result = ref<number | undefined>(undefined);
const simbolo = ref<string | undefined>(undefined);
const fuel_1 = ref<Fuel>(fuels[0]);
const unit_1 = ref<Fuel>(units_1.value[0]);
const fuel_2 = ref<Fuel>(fuels[0]);
const unit_2 = ref<Fuel>(units_2.value[0]);

const toggleModal1 = () => {
	active_modal_1.value = !active_modal_1.value;
};

const toggleModal2 = () => {
	active_modal_2.value = !active_modal_2.value;
};

const toggleModal3 = () => {
	active_modal_3.value = !active_modal_3.value;
};

const toggleModal4 = () => {
	active_modal_4.value = !active_modal_4.value;
};

const selectModal1 = (i: number) => {
	fuel_1.value = fuels[i];
	if (fuel_1.value[0] !== 1) {
		units_1.value = store.getUnits(fuel_1.value[0]);
	} else {
		units_1.value = [[1, 'Producto', 5]];
	}
	unit_1.value = units_1.value[0];
	toggleModal1();
};

const selectModal2 = (i: number) => {
	unit_1.value = units_1.value[i];
	toggleModal2();
};

const selectModal3 = (i: number) => {
	fuel_2.value = fuels[i];
	if (fuel_2.value[0] !== 1) {
		units_2.value = store.getUnits(fuel_2.value[0]);
	} else {
		units_2.value = [[1, 'Producto', 5]];
	}
	unit_2.value = units_2.value[0];
	toggleModal3();
};

const selectModal4 = (i: number) => {
	unit_2.value = units_2.value[i];
	toggleModal4();
};

const validForm = computed(() => {
	return (
		!!cant.value &&
		fuel_1.value[0] !== 1 &&
		fuel_2.value[0] !== 1 &&
		unit_1.value[0] !== units_1.value[0][0] &&
		unit_2.value[0] !== units_2.value[0][0]
	);
});

const convert = () => {
	if (validForm.value) {
		[result.value, simbolo.value] = store.convertEnergy(cant.value as number, unit_1.value, unit_2.value);
	}
};

const clear = () => {
	units_1.value = [[1, 'Producto', 5]];
	units_2.value = [[1, 'Producto', 5]];
	cant.value = undefined;
	result.value = undefined;
	simbolo.value = undefined;
	fuel_1.value = fuels[0];
	fuel_2.value = fuels[0];
	unit_1.value = units_1.value[0];
	unit_2.value = units_2.value[0];
};
</script>

<style lang="scss">
.modal-card-select {
	width: 80vw;
}
</style>
