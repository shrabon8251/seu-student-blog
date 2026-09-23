# SEU Student Blog — Backend (Spring Boot + Thymeleaf + MongoDB)

Phase 2 of the SEU Student Blog project: turns the static frontend prototype into a
real server-rendered app.

## Stack
- Java 17, Spring Boot 3.3
- Spring MVC + Thymeleaf (server-rendered templates)
- Spring Data MongoDB
- Spring Security (form login for `/admin/**`)
- Maven

## Project structure
```
src/main/java/com/seu/studentblog/
  config/       SecurityConfig, AdminUserDetailsService, DataSeeder
  controller/   SiteController (public), AdminController, AuthController
  model/        Blog, Category, Admin (MongoDB documents)
  repository/   BlogRepository, CategoryRepository, AdminRepository
  service/      BlogService, CategoryService

src/main/resources/
  templates/            index, blogs, blog-details, categories, about, contact
  templates/fragments/  navbar, footer, blog-card (reusable Thymeleaf fragments)
  templates/admin/      login, dashboard, blogs, blog-form, categories
  templates/admin/fragments/  sidebar
  static/css/           global.css, admin.css (carried over from the UI prototype)
  static/js/            main.js, admin.js
  application.properties
```

## Run locally
1. Start MongoDB locally (default: `mongodb://localhost:27017`).
2. `mvn spring-boot:run`
3. Visit `http://localhost:8080`

On first run, `DataSeeder` inserts sample categories, two sample blogs, and one
admin account:
- **Email:** admin@seublog.edu
- **Password:** ChangeMe123!

Change this password (or remove the seeder) before any real deployment.

## Notes / next steps
- `SecurityConfig` currently disables CSRF for simplicity — re-enable it once
  `th:action` forms include the CSRF token (`<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>`).
- Featured-image upload is wired only as a URL field for now; add a
  `MultipartFile` upload endpoint using `app.upload.dir` from
  `application.properties` to support real file uploads.
- `Blog.content` is rendered with `th:utext` (raw HTML) — sanitize any
  user-submitted HTML before saving once the rich-text editor is wired up.
- Category/blog "Edit" and "Delete" actions in `admin/categories.html` are
  UI-only placeholders; wire them to `CategoryService` the same way
  `AdminController` already does for blogs.
