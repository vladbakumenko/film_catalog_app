var filmApi = Vue.resource('/films')

Vue.component('films-list', {
  props: ['films'],
  template: '<div><div v-for="film in films"> {{ film }} </div></div>',
  created: function() {
    filmApi.get().then(result =>
        result.json().then(data =>
            data.forEach(film => this.films.push(film))
        )
    )
  }
});

var app = new Vue({
  el: '#app',
  template: '<films-list :films="films" />',
  data: {
    films: []
  }
});