<template>
  <q-page>
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
      <q-btn color="purple">Торг</q-btn>
      <q-btn color="purple" @click="getFloor">Этаж расположения квартиры</q-btn>
      <q-btn color="purple">Площадь квартиры</q-btn>
      <q-btn color="purple">Площадь кухни</q-btn>
      <q-btn color="purple">Балкон/лоджия</q-btn>
      <q-btn color="purple">Расстояние до метро</q-btn>
      <q-btn color="purple">Состояние отделки</q-btn>
      <!-- <q-btn color="purple" label="First" icon="timeline" />
      <q-btn color="purple" label="Second" icon="visibility" /> -->
    </q-btn-group>

    <q-page-container>
      <router-view />
    </q-page-container>

    <!-- <div class="q-pt-xl">
      <q-markup-table class="text-center text-h4" bordered>
        <thead>
          <tr>
            <th colspan="2" rowspan="2">
              <span class="text-bold">Этаж расположения квартиры</span>
            </th>
            <th colspan="3">Объект аналог</th>
          </tr>
          <tr>
            <th>Первый этаж</th>
            <th>Средние этажи</th>
            <th>Последний этаж</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td rowspan="3">Объект оценки</td>
            <td>Первый этаж</td>
            <td>0,0%</td>
            <td>-7,0%</td>
            <td>-3,1%</td>
          </tr>
          <tr>
            <td>Средние этажи</td>
            <td>7,5%</td>
            <td>0,0%</td>
            <td>4,2%</td>
          </tr>
          <tr>
            <td>Последний этаж</td>
            <td>3,2%</td>
            <td>-4,0%</td>
            <td>0,0%</td>
          </tr>
        </tbody>
      </q-markup-table>
    </div> -->
  </q-page>
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
      this.$router.push("floor");
    },
  },
};
</script>
