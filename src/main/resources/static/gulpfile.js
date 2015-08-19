// Include gulp
var gulp = require('gulp');
// Include plugins que previamente ya instalamos
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var minifyCSS = require('gulp-minify-css');
ngAnnotate  = require('gulp-ng-annotate'),
// Concatenate JS Files
gulp.task('scripts', function() {
    return gulp.src('js/*.js')
        .pipe(concat('main.js'))
        .pipe(ngAnnotate())
        .pipe(rename({suffix: '.min'}))
        .pipe(uglify())
        .pipe(gulp.dest('build/js'));
});


// Default Task
gulp.task('default', ['scripts','sass','less','watch']);
var sass = require('gulp-ruby-sass');

gulp.task('watch', function() {
    // Watch .js files
    gulp.watch('js/*.js', ['scripts']);
    // Watch .scss files
    gulp.watch('scss/*.scss', ['sass']);
    // Watch image files

});

gulp.task('sass', function() {
    return sass('scss/style.scss', {style: 'compressed'})
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest('build/css'));
});

var less = require('gulp-less');



gulp.task('less', function() {
    gulp.src('less/estilos.less')
        .pipe(concat('layout.css'))
        .pipe(less())
        .pipe(minifyCSS())
        .pipe(gulp.dest('build/css'));
});

