<template>
  <!-- <q-btn @click="goBackInHistory" color="teal" flat>
      <q-icon left size="3em" name="keyboard_backspace" />
      <div>К предыдущей странице</div>
    </q-btn> -->

  <!-- <div class="row justify-center">
      <q-btn unelevated color="primary" label="Торг"></q-btn>
      <q-btn>Этаж расположения квартиры</q-btn>
      <q-btn>Площадь квартиры</q-btn>
      <q-btn>Площадь кухни</q-btn>
      <q-btn>Балкон/лоджия</q-btn>
      <q-btn>Расстояние до метро</q-btn>
      <q-btn>Состояние отделки</q-btn>
    </div> -->
  <q-btn-group spread>
    <q-btn
      @click="goBackInHistory"
      color="purple"
      icon="keyboard_backspace"
      label=" Назад"
    >
    </q-btn>
    <q-btn color="purple" @click="getAuction">Торг</q-btn>
    <q-btn color="purple" @click="getFloor">Этаж расположения квартиры</q-btn>
    <q-btn color="purple" @click="getFlatArea">Площадь квартиры</q-btn>
    <q-btn color="purple" @click="getKitchenArea">Площадь кухни</q-btn>
    <q-btn color="purple" @click="getBalcony">Балкон/лоджия</q-btn>
    <q-btn color="purple" @click="getMetro">Расстояние до метро</q-btn>
    <q-btn color="purple" @click="getCondition">Состояние отделки</q-btn>
  </q-btn-group>

  <router-view />
</template>

<script>
export default {
  name: "UserPageCorrections",
  data() {
    return {
      data: [],
      columns: [],
      props: ["", "Значение"],
    };
  },
  methods: {
    goBackInHistory() {
      this.$router.go(-1);
    },
    getFloor() {
      this.$router.push(`/user/${this.$route.params.login}/corrections/floor`);
    },
    getAuction() {
      this.$router.push(
        `/user/${this.$route.params.login}/corrections/auction`
      );
    },
    getFlatArea() {
      this.$router.push(
        `/user/${this.$route.params.login}/corrections/flatArea`
      );
    },
    getKitchenArea() {
      this.$router.push(
        `/user/${this.$route.params.login}/corrections/kitchenArea`
      );
    },
    getBalcony() {
      this.$router.push(
        `/user/${this.$route.params.login}/corrections/balcony`
      );
    },
    getMetro() {
      this.$router.push(`/user/${this.$route.params.login}/corrections/metro`);
    },
    getCondition() {
      this.$router.push(
        `/user/${this.$route.params.login}/corrections/condition`
      );
    },
  },
};
</script>
