var gulp = require('gulp'),
    webserver = require('gulp-webserver'),
    connect = require('gulp-connect');
 
gulp.task('connect', function() {
  connect.server();
});
gulp.task('default', ['connect']);
 

gulp.task('webserver', function() {
  gulp.src('app')
    .pipe(webserver({
      livereload: true,
      directoryListing: true,
      open: true
    }));
});

gulp.task('default', function() {
  // place code for your default task here
});