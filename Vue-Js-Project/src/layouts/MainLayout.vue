<template>
  <q-layout>
    <q-header>
      <div class="row justify-end bg-dark q-pa-lg text-weight-light">
        <q-btn to="/" flat no-caps>
          <q-icon left size="3em" name="home" />
          <div>Главная</div>
        </q-btn>
        <q-btn to="/login" flat no-caps>
          <q-icon left size="3em" name="login" />
          <div>Вход</div>
        </q-btn>
        <q-btn to="/signup" flat no-caps>
          <q-icon left size="3em" name="how_to_reg" />
          <div>Регистрация</div>
        </q-btn>
      </div>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "MainLayout",

  components: {},
};
</script>
