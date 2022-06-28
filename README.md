# RickAndMortyInfoApp

## Общая информация

Разработка проекта ведется по Gitflow. Для этого были созданы основные ветки:
- master - для коммита релизных версий проекта;
- develop - для основных коммитов в хоте промежуточной разработки между релизами.</br></br>
Также по мере работы над проектом добавляются ветки feature для разработки отдельных частей приложения.
</br>
Приложение разрабатывается в соотвествии с Clean Architecture и с архитектурой MVVM.

### Реализованы следующие основные требования к приложению:

1. Splash Screen - показывается как заставка при запуске приложения;
2. Пользовательский интерфейс основного экрана;
3. Продолжение следует...

## Подробная информация

### Реализация Splash Screen

Для реализации заставки создана отдельная активность, указанная в AndroidManifest как LAUNCHER. Для этой активности создана тема, в фон которой поставлено нужное изображение. Тема указана для SplashScreenActivity в AndroidManifest. В методе SplashScreenActivity.onCreate запускается MainActivity и вызывается метод finish. Пока выполняется метод MainActivity.onCreate, тема SplashScreenActivity отображается на экране.

### Реализация пользовательского интерфейса основного экрана

Разметка MainActivity содержит только FragmentContainerView, в котором будут размещаться либо фрагмент основного экрана, либо фрагмент деталей.</br></br> Разметка основного экрана представлена одним фрагментом (MainFragment), содержащим RecyclerView и BottomNavigationView. В дальнейшем будут добавлены SearchView
и фильтр. Для фрагмента создана ViewModel, содержащая LiveData&lt;List&gt; для хранения элементов списка и selectedMenuItemId для хранения id выбранного экрана. При выборе элемента BottomNavigationView во ViewModel.LiveData&lt;List&gt; загружается список соответствующих элементов.</br></br>
ViewModel добавляется в MainFragment через Dagger 2. Для ViewModels создана ViewModelsFactory, создающая ViewModels из Map через Multibindings. Также для DI созданы модули для ViewModels и UseCases, объединенные в AppComponent. Для создания экземпляра AppComponent создан класс App : Application, указанный в 
AndroidManifest.