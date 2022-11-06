<template>
  <q-layout>
    <q-header
      class="row items-center text-h3 text-weight-light text-white text-center"
    >
      <div class="col-7 row justify-start items-center q-pl-xl">
        <q-btn
          class="col-auto text-h3 text-weight-light"
          flat
          no-caps
          @click="toMain"
        >
          Главная
        </q-btn>

        <q-btn
          class="col-auto text-h3 text-weight-light"
          flat
          no-caps
          @click="toCorrections"
        >
          Коррекции
        </q-btn>
      </div>

      <div class="col-5 justify-end row items-center q-pr-lg">
        <q-btn class="col-auto" to="/" flat>
          <q-icon left size="3em" name="logout" />
          <div>Выйти из аккаунта</div>
        </q-btn>

        <div class="col-auto text-weight-light text-h6 q-pr-md">
          <span><q-icon size="2em" name="person" /></span>
          <span> {{ $route.params.login }}</span>
        </div>
      </div>
    </q-header>
    <q-page-container>
      <router-view v-slot="{ Component }">
        <keep-alive>
          <component :is="Component" />
        </keep-alive>
      </router-view>
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "UserPage",
  components: {},
  data() {
    return {};
  },
  methods: {
    toMain() {
      this.$router.push(`/user/${this.$route.params.login}/`);
    },
    toCorrections() {
      this.$router.push(`/user/${this.$route.params.login}/corrections`);
    },
  },
  mounted() {
    this.toMain();
  },
};
</script>

<style></style>
