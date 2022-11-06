<template>
  <q-markup-table class="text-center text-h4" bordered>
    <thead>
      <tr>
        <th colspan="2" rowspan="2">Состояние отделки</th>
        <th colspan="3">Объект-аналог</th>
      </tr>
      <tr>
        <th>Без отделки</th>
        <th>Эконом</th>
        <th>Улучшенный</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td rowspan="3">Объект оценки</td>
        <td>Без отделки</td>
        <td>0</td>
        <td>-13 400</td>
        <td>-20 100</td>
      </tr>
      <tr>
        <td>Эконом</td>
        <td>13 400</td>
        <td>0</td>
        <td>-6 700</td>
      </tr>
      <tr>
        <td>Улучшенный</td>
        <td>20 100</td>
        <td>6 700</td>
        <td>0</td>
      </tr>
    </tbody>
  </q-markup-table>
</template>

<style>
.th {
  font-size: 4.5rem;
}
td {
  font-size: 4.5rem;
}
</style>
