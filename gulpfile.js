var gulp = require('gulp'),
    webserver = require('gulp-webserver'),
    connect = require('gulp-connect'),
    jshint = require('gulp-jshint'),
    concat = require('gulp-concat'),
    uglify = require('gulp');

var srcFiles =["app/*.js", "app/login/*.js","app/register/*.js","app/products/*.js"];
 
gulp.task('connect', function() {
  connect.server();
});

gulp.task('webserver', function() {
  gulp.src('app')
    .pipe(webserver({
      livereload: true,
      directoryListing: true,
      open: true
    }));
});

gulp.task('concat', function() {
  return gulp.src(srcFiles)
    .pipe(concat('allfilecombine.js'))
    .pipe(gulp.dest('./dist/'));
});   

gulp.task('uglify', function(){
    return gulp.src(srcFiles)
        .pipe(concat('concat.js'))
        .pipe(gulp.dest('dist'))
        .pipe(rename('uglify.js'))
        .pipe(uglify())
        .pipe(gulp.dest('dist'));
});


gulp.task('lint', function() {
  return gulp.src(srcFiles)
    .pipe(jshint())
    .pipe(jshint.reporter('YOUR_REPORTER_HERE'));
});

gulp.task('default', ['connect']);