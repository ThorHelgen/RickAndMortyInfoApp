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
3. Domain слой из Clean Architecture;
4. Data слой из Clean Architecture;
5. Продолжение следует...


## Подробная информация

### Реализация Splash Screen

Для реализации заставки создана отдельная активность, указанная в AndroidManifest как LAUNCHER. Для этой активности создана тема, в фон которой поставлено нужное изображение. Тема указана для SplashScreenActivity в AndroidManifest. В методе SplashScreenActivity.onCreate запускается MainActivity и вызывается метод finish. Пока выполняется метод MainActivity.onCreate, тема SplashScreenActivity отображается на экране.

### Реализация пользовательского интерфейса основного экрана

Разметка MainActivity содержит только FragmentContainerView, в котором будут размещаться либо фрагмент основного экрана, либо фрагмент деталей.</br></br> Разметка основного экрана представлена одним фрагментом (MainFragment), содержащим RecyclerView и BottomNavigationView. В дальнейшем будут добавлены SearchView
и фильтр. Для фрагмента создана ViewModel, содержащая LiveData&lt;List&gt; для хранения элементов списка и selectedMenuItemId для хранения id выбранного экрана. При выборе элемента BottomNavigationView во ViewModel.LiveData&lt;List&gt; загружается список соответствующих элементов.</br></br>
ViewModel добавляется в MainFragment через Dagger 2. Для ViewModels создана ViewModelsFactory, создающая ViewModels из Map через Multibindings. Также для DI созданы модули для ViewModels и UseCases, объединенные в AppComponent. Для создания экземпляра AppComponent создан класс App : Application, указанный в 
AndroidManifest.

### Реализация Domain слоя

В Domain слое реализованы 2 основных пакета: entities и usecases. В первом пакете содержатся классы - представления все сущностей, которые используются в usecases. Во втором - интерфейс и реализация юзкейсов, содержащие все основные функции, которые должно выполнять приложение, а также интрефейсы локального и удаленного репозиториев, с которыми взаимодействуют юзкейсы.</br>
Кеширование реализовано в юзкейсах по следующему алгоритму:
1. Попытка загрузить данные из кэша;
2. Если нет кешированных данных, попытка загрузить данные из удаленного репозитория;
3. Если получены данные из удаленного репозитория, кешировать их.</br></br>

Для юзкейсов в di добавлен модуль UseCasesModule с соответствующим методом provide.

### Реализация Data слоя

В Data слое реализованы 2 пакета: local и remote, в каждом из которых дополнительно содержатся пакеты entities и mappers. Пакеты entities в local и remote, как и в Domain слое, предоставляют классы для используемых сущностей. Пакеты mappers - классы для конвертации сущностей Data слоя в сущности Domain слоя и (или) наоборот.</br>
В пакете local реализовано кеширование данных с использованием библиотеки Room. Для взаимодействия с Room созданы классы CacheDAO и CacheDB, в качестве Entity для БД Room используются классы из пакета entities. Для использования кеша в Domain слое создан класс LocalRepositoryImpl, реализующий интерфейс LocalRepository из Domain слоя.</br>
Для внедрения зависимостей кеша в di добавлен модуль CacheModule с методами provide для CacheDB и LocalRepository.
В пакете remote реализовано взаимодействие с удаленным API с использованием библиотек: Retrofit для получения данных по протоколу http и Moshi для конвертации полученных JSON в классы kotlin. Для использования Retrofit создан интерфейс ApiService с необходимыми методами GET. Для использования API в юзкейсах создан класс RemoteRepositoryImpl, реализующий интерфейс RemoteRepository из Domain слоя.</br>
В di для API создан модуль NetworkModule. Экземпляры конвертера Moshi, Retrofit и ApiService создаются через di в соответствующих методах provide. Также в NetworkModule добавлен provide для RemoteRepository.