<template>
  <q-page class="indexPage flex flex-center column text-white">
    <div class="text-h4 q-pb-xl text-weight-light">Регистрация</div>
    <q-form @submit="submit" @reset="reset" class="q-gutter-md">
      <q-input
        dark
        color="yellow-3"
        filled
        v-model="loginInput"
        label="Придумайте логин"
        lazy-rules
        :rules="[(val) => (val && val.length > 0) || 'Придумайте свой логин']"
      >
        <template v-slot:prepend>
          <q-icon name="person" />
        </template>
      </q-input>

      <q-input
        dark
        color="yellow-3"
        filled
        v-model="passwordInput"
        label="Придумайте пароль"
        lazy-rules
        :rules="[
          (val) => (val !== null && val !== '') || 'Придумайте свой пароль',
          (val) =>
            (val && val.length > 5) ||
            'Пароль должен содержать более 5 символов',
        ]"
      >
        <template v-slot:prepend>
          <q-icon name="lock"></q-icon>
        </template>
      </q-input>

      <q-input
        dark
        color="yellow-3"
        filled
        v-model="passwordRetypeInput"
        label="Введите пароль еще раз"
        lazy-rules
        :rules="[
          (val) => (val !== null && val !== '') || 'Введите пароль еще раз',
          (val) => val === passwordInput || 'Пароли не совпадают',
        ]"
      >
        <template v-slot:prepend>
          <q-icon name="lock"></q-icon>
        </template>
      </q-input>

      <div class="text-center">
        <q-btn label="Подтвердить" type="submit" color="primary" />
        <q-btn
          label="Сбросить"
          type="reset"
          color="primary"
          flat
          class="q-ml-sm"
        />
      </div>
    </q-form>
  </q-page>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginPage",
  data() {
    return {
      loginInput: "",
      passwordInput: "",
      passwordRetypeInput: "",

      login: "",
      password: "",
    };
  },
  methods: {
    submit() {
      this.login = this.loginInput;
      this.password = this.passwordInput;

      // const login = this.login;

      const url = "http://127.0.0.1:8081/user/register";
      axios
        .post(url, { login: this.login, password: this.password })
        .then((response) => {
          if (response.status === 200) {
            this.$q.notify({
              type: "positive",
              message: "Пользователь успешно зарегистрирован!",
            });
            this.$router.push("/login");
          } else {
            this.login = "";
            this.password = "";
            this.loginInput = "";
            this.passwordInput = "";
            this.passwordRetypeInput = "";

            this.$q.notify({
              type: "negative",
              message: "Такой пользователь уже существует",
            });
          }
        })
        .catch((error) => {
          this.$q.notify({
            type: "negative",
            message: "Произошла ошибка",
          });
          console.log(error);
        });
    },
    reset() {
      this.loginInput = "";
      this.passwordInput = "";
      this.passwordRetypeInput = "";
    },
  },
};
</script>

<style>
.indexPage {
  background: #780206; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to bottom,
    #061161,
    #780206
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to bottom,
    #061161,
    #780206
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style>
