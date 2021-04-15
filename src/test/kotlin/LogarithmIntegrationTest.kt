import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito
import kotlin.math.abs
import kotlin.math.max

class LogarithmIntegrationTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            " 1 , 0 , 2 , 0.693147180559945 , 0 ",
            " 2.71828182845904 , 1 , 2 , 0.693147180559945 , 1.44269504088896 ",
            " 7.38905609893065 , 2 , 2 , 0.693147180559945 , 2.88539008177793 ",
            " 20.0855369231877 , 3 , 2 , 0.693147180559945 , 4.32808512266689 ",
            " 0.367879441171442 , -1 , 2 , 0.693147180559945 , -1.44269504088896 ",
            " 0.135335283236613 , -2 , 2 , 0.693147180559945 , -2.88539008177793 ",
            " 0.049787068367864 , -3 , 2 , 0.693147180559945 , -4.32808512266689 ",
            " 2 , 0.693147180559945 , 2 , 0.693147180559945 , 1 ",
            " 5 , 1.6094379124341 , 2 , 0.693147180559945 , 2.32192809488736 ",
            " 10 , 2.30258509299405 , 2 , 0.693147180559945 , 3.32192809488736 ",
            " 100 , 4.60517018598809 , 2 , 0.693147180559945 , 6.64385618977473 ",
            " 0.9 , -0.105360515657826 , 2 , 0.693147180559945 , -0.15200309344505 ",
            " 2.61828182845904 , 0.962518311983765 , 2 , 0.693147180559945 , 1.38862039546379 ",
            " 7.28905609893065 , 1.98637405875277 , 2 , 0.693147180559945 , 2.8657320039131 ",
            " 19.9855369231877 , 2.99500885811151 , 2 , 0.693147180559945 , 4.32088442701599 ",
            " 0.267879441171442 , -1.31721824605773 , 2 , 0.693147180559945 , -1.90034423135595 ",
            " 0.035335283236613 , -3.342873289119 , 2 , 0.693147180559945 , -4.82274671653216 ",
            " 1.9 , 0.641853886172395 , 2 , 0.693147180559945 , 0.925999418556223 ",
            " 4.9 , 1.58923520511658 , 2 , 0.693147180559945 , 2.29278174922785 ",
            " 9.9 , 2.29253475714054 , 2 , 0.693147180559945 , 3.30742852519225 ",
            " 99.9 , 4.60416968565451 , 2 , 0.693147180559945 , 6.64241277290506 ",
            " 1.1 , 0.095310179804325 , 2 , 0.693147180559945 , 0.137503523749935 ",
            " 2.81828182845904 , 1.03612741857483 , 2 , 0.693147180559945 , 1.494815888507 ",
            " 7.48905609893065 , 2.01344276808316 , 2 , 0.693147180559945 , 2.90478389662733 ",
            " 20.1855369231877 , 3.00496635405951 , 2 , 0.693147180559945 , 4.33525005703984 ",
            " 0.467879441171442 , -0.759544620584711 , 2 , 0.693147180559945 , -1.09579125745145 ",
            " 0.235335283236613 , -1.44674404448335 , 2 , 0.693147180559945 , -2.08721045841176 ",
            " 0.149787068367864 , -1.89854053760754 , 2 , 0.693147180559945 , -2.73901501853306 ",
            " 2.1 , 0.741937344729377 , 2 , 0.693147180559945 , 1.0703893278914 ",
            " 5.1 , 1.62924053973028 , 2 , 0.693147180559945 , 2.35049724708413 ",
            " 10.1 , 2.31253542384721 , 2 , 0.693147180559945 , 3.33628338786443 ",
            " 100.1 , 4.60616968632117 , 2 , 0.693147180559945 , 6.64529816394863 ",
            " 1 , 0 , 5 , 1.6094379124341 , 0 ",
            " 2.71828182845904 , 1 , 5 , 1.6094379124341 , 0.621334934559612 ",
            " 7.38905609893065 , 2 , 5 , 1.6094379124341 , 1.24266986911922 ",
            " 20.0855369231877 , 3 , 5 , 1.6094379124341 , 1.86400480367884 ",
            " 0.367879441171442 , -1 , 5 , 1.6094379124341 , -0.621334934559612 ",
            " 0.135335283236613 , -2 , 5 , 1.6094379124341 , -1.24266986911922 ",
            " 0.049787068367864 , -3 , 5 , 1.6094379124341 , -1.86400480367884 ",
            " 2 , 0.693147180559945 , 5 , 1.6094379124341 , 0.430676558073393 ",
            " 5 , 1.6094379124341 , 5 , 1.6094379124341 , 1 ",
            " 10 , 2.30258509299405 , 5 , 1.6094379124341 , 1.43067655807339 ",
            " 100 , 4.60517018598809 , 5 , 1.6094379124341 , 2.86135311614679 ",
            " 0.9 , -0.105360515657826 , 5 , 1.6094379124341 , -0.065464169101423 ",
            " 2.61828182845904 , 0.962518311983765 , 5 , 1.6094379124341 , 0.59804625238886 ",
            " 7.28905609893065 , 1.98637405875277 , 5 , 1.6094379124341 , 1.23420359580606 ",
            " 19.9855369231877 , 2.99500885811151 , 5 , 1.6094379124341 , 1.86090363286017 ",
            " 0.267879441171442 , -1.31721824605773 , 5 , 1.6094379124341 , -0.818433712715006 ",
            " 0.035335283236613 , -3.342873289119 , 5 , 1.6094379124341 , -2.07704395633583 ",
            " 1.9 , 0.641853886172395 , 5 , 1.6094379124341 , 0.398806242361757 ",
            " 4.9 , 1.58923520511658 , 5 , 1.6094379124341 , 0.987447352170942 ",
            " 9.9 , 2.29253475714054 , 5 , 1.6094379124341 , 1.42443193330356 ",
            " 99.9 , 4.60416968565451 , 5 , 1.6094379124341 , 2.86073147033749 ",
            " 1.1 , 0.095310179804325 , 5 , 1.6094379124341 , 0.059219544331585 ",
            " 2.81828182845904 , 1.03612741857483 , 5 , 1.6094379124341 , 0.643782161815614 ",
            " 7.48905609893065 , 2.01344276808316 , 5 , 1.6094379124341 , 1.25102233054648 ",
            " 20.1855369231877 , 3.00496635405951 , 5 , 1.6094379124341 , 1.8670905729534 ",
            " 0.467879441171442 , -0.759544620584711 , 5 , 1.6094379124341 , -0.471931607126107 ",
            " 0.235335283236613 , -1.44674404448335 , 5 , 1.6094379124341 , -0.898912616203567 ",
            " 0.149787068367864 , -1.89854053760754 , 5 , 1.6094379124341 , -1.17962956069315 ",
            " 2.1 , 0.741937344729377 , 5 , 1.6094379124341 , 0.46099159153476 ",
            " 5.1 , 1.62924053973028 , 5 , 1.6094379124341 , 1.01230406413518 ",
            " 10.1 , 2.31253542384721 , 5 , 1.6094379124341 , 1.43685904624289 ",
            " 100.1 , 4.60616968632117 , 5 , 1.6094379124341 , 2.86197414062083 ",
            " 1 , 0 , 10 , 2.30258509299405 , 0 ",
            " 2.71828182845904 , 1 , 10 , 2.30258509299405 , 0.434294481903252 ",
            " 7.38905609893065 , 2 , 10 , 2.30258509299405 , 0.868588963806504 ",
            " 20.0855369231877 , 3 , 10 , 2.30258509299405 , 1.30288344570976 ",
            " 0.367879441171442 , -1 , 10 , 2.30258509299405 , -0.434294481903252 ",
            " 0.135335283236613 , -2 , 10 , 2.30258509299405 , -0.868588963806504 ",
            " 0.049787068367864 , -3 , 10 , 2.30258509299405 , -1.30288344570976 ",
            " 2 , 0.693147180559945 , 10 , 2.30258509299405 , 0.301029995663981 ",
            " 5 , 1.6094379124341 , 10 , 2.30258509299405 , 0.698970004336019 ",
            " 10 , 2.30258509299405 , 10 , 2.30258509299405 , 1 ",
            " 100 , 4.60517018598809 , 10 , 2.30258509299405 , 2 ",
            " 0.9 , -0.105360515657826 , 10 , 2.30258509299405 , -0.045757490560675 ",
            " 2.61828182845904 , 0.962518311983765 , 10 , 2.30258509299405 , 0.418016391625382 ",
            " 7.28905609893065 , 1.98637405875277 , 10 , 2.30258509299405 , 0.862671292712093 ",
            " 19.9855369231877 , 2.99500885811151 , 10 , 2.30258509299405 , 1.30071582032919 ",
            " 0.267879441171442 , -1.31721824605773 , 10 , 2.30258509299405 , -0.572060615725152 ",
            " 0.035335283236613 , -3.342873289119 , 10 , 2.30258509299405 , -1.45179142316615 ",
            " 1.9 , 0.641853886172395 , 10 , 2.30258509299405 , 0.278753600952829 ",
            " 4.9 , 1.58923520511658 , 10 , 2.30258509299405 , 0.690196080028514 ",
            " 9.9 , 2.29253475714054 , 10 , 2.30258509299405 , 0.99563519459755 ",
            " 99.9 , 4.60416968565451 , 10 , 2.30258509299405 , 1.99956548822598 ",
            " 1.1 , 0.095310179804325 , 10 , 2.30258509299405 , 0.041392685158225 ",
            " 2.81828182845904 , 1.03612741857483 , 10 , 2.30258509299405 , 0.449984420435711 ",
            " 7.48905609893065 , 2.01344276808316 , 10 , 2.30258509299405 , 0.874427083806527 ",
            " 20.1855369231877 , 3.00496635405951 , 10 , 2.30258509299405 , 1.30504030587298 ",
            " 0.467879441171442 , -0.759544620584711 , 10 , 2.30258509299405 , -0.329866037479239 ",
            " 0.235335283236613 , -1.44674404448335 , 10 , 2.30258509299405 , -0.628312955245509 ",
            " 0.149787068367864 , -1.89854053760754 , 10 , 2.30258509299405 , -0.824525679152586 ",
            " 2.1 , 0.741937344729377 , 10 , 2.30258509299405 , 0.322219294733919 ",
            " 5.1 , 1.62924053973028 , 10 , 2.30258509299405 , 0.707570176097936 ",
            " 10.1 , 2.31253542384721 , 10 , 2.30258509299405 , 1.00432137378264 ",
            " 100.1 , 4.60616968632117 , 10 , 2.30258509299405 , 2.00043407747932 ",
        ]
    )
    fun logTestMock(x: Double, lnx: Double, base: Double, lnBase: Double, expected: Double) {
        val spy = Mockito.spy(Algebra(0.0))
        Mockito.`when`(spy.ln(x)).thenReturn(lnx)
        Mockito.`when`(spy.ln(base)).thenReturn(lnBase)
        Assertions.assertEquals(expected, spy.log(x, base), 0.02 * max(1.0, abs(expected)))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            " 1 , 0 , 0 ",
            " 2.71828182845904 , 1 , 1.44269504088896 ",
            " 7.38905609893065 , 2 , 2.88539008177793 ",
            " 20.0855369231877 , 3 , 4.32808512266689 ",
            " 0.367879441171442 , -1 , -1.44269504088896 ",
            " 0.135335283236613 , -2 , -2.88539008177793 ",
            " 0.049787068367864 , -3 , -4.32808512266689 ",
            " 2 , 0.693147180559945 , 1 ",
            " 5 , 1.6094379124341 , 2.32192809488736 ",
            " 10 , 2.30258509299405 , 3.32192809488736 ",
            " 100 , 4.60517018598809 , 6.64385618977473 ",
            " 0.9 , -0.105360515657826 , -0.15200309344505 ",
            " 2.61828182845904 , 0.962518311983765 , 1.38862039546379 ",
            " 7.28905609893065 , 1.98637405875277 , 2.8657320039131 ",
            " 19.9855369231877 , 2.99500885811151 , 4.32088442701599 ",
            " 0.267879441171442 , -1.31721824605773 , -1.90034423135595 ",
            " 0.035335283236613 , -3.342873289119 , -4.82274671653216 ",
            " 1.9 , 0.641853886172395 , 0.925999418556223 ",
            " 4.9 , 1.58923520511658 , 2.29278174922785 ",
            " 9.9 , 2.29253475714054 , 3.30742852519225 ",
            " 99.9 , 4.60416968565451 , 6.64241277290506 ",
            " 1.1 , 0.095310179804325 , 0.137503523749935 ",
            " 2.81828182845904 , 1.03612741857483 , 1.494815888507 ",
            " 7.48905609893065 , 2.01344276808316 , 2.90478389662733 ",
            " 20.1855369231877 , 3.00496635405951 , 4.33525005703984 ",
            " 0.467879441171442 , -0.759544620584711 , -1.09579125745145 ",
            " 0.235335283236613 , -1.44674404448335 , -2.08721045841176 ",
            " 0.149787068367864 , -1.89854053760754 , -2.73901501853306 ",
            " 2.1 , 0.741937344729377 , 1.0703893278914 ",
            " 5.1 , 1.62924053973028 , 2.35049724708413 ",
            " 10.1 , 2.31253542384721 , 3.33628338786443 ",
            " 100.1 , 4.60616968632117 , 6.64529816394863 ",
        ]
    )
    fun log2TestMock(x: Double, lnx: Double, expected: Double) {
        val spy = Mockito.spy(Algebra(0.0))
        Mockito.`when`(spy.ln(x)).thenReturn(lnx)
        Mockito.`when`(spy.ln(2.0)).thenReturn(0.693147180559945)
        Assertions.assertEquals(expected, spy.log_2(x), 0.02 * max(1.0, abs(expected)))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            " 1 , 0 , 0 ",
            " 2.71828182845904 , 1 , 0.621334934559612 ",
            " 7.38905609893065 , 2 , 1.24266986911922 ",
            " 20.0855369231877 , 3 , 1.86400480367884 ",
            " 0.367879441171442 , -1 , -0.621334934559612 ",
            " 0.135335283236613 , -2 , -1.24266986911922 ",
            " 0.049787068367864 , -3 , -1.86400480367884 ",
            " 2 , 0.693147180559945 , 0.430676558073393 ",
            " 5 , 1.6094379124341 , 1 ",
            " 10 , 2.30258509299405 , 1.43067655807339 ",
            " 100 , 4.60517018598809 , 2.86135311614679 ",
            " 0.9 , -0.105360515657826 , -0.065464169101423 ",
            " 2.61828182845904 , 0.962518311983765 , 0.59804625238886 ",
            " 7.28905609893065 , 1.98637405875277 , 1.23420359580606 ",
            " 19.9855369231877 , 2.99500885811151 , 1.86090363286017 ",
            " 0.267879441171442 , -1.31721824605773 , -0.818433712715006 ",
            " 0.035335283236613 , -3.342873289119 , -2.07704395633583 ",
            " 1.9 , 0.641853886172395 , 0.398806242361757 ",
            " 4.9 , 1.58923520511658 , 0.987447352170942 ",
            " 9.9 , 2.29253475714054 , 1.42443193330356 ",
            " 99.9 , 4.60416968565451 , 2.86073147033749 ",
            " 1.1 , 0.095310179804325 , 0.059219544331585 ",
            " 2.81828182845904 , 1.03612741857483 , 0.643782161815614 ",
            " 7.48905609893065 , 2.01344276808316 , 1.25102233054648 ",
            " 20.1855369231877 , 3.00496635405951 , 1.8670905729534 ",
            " 0.467879441171442 , -0.759544620584711 , -0.471931607126107 ",
            " 0.235335283236613 , -1.44674404448335 , -0.898912616203567 ",
            " 0.149787068367864 , -1.89854053760754 , -1.17962956069315 ",
            " 2.1 , 0.741937344729377 , 0.46099159153476 ",
            " 5.1 , 1.62924053973028 , 1.01230406413518 ",
            " 10.1 , 2.31253542384721 , 1.43685904624289 ",
            " 100.1 , 4.60616968632117 , 2.86197414062083 ",
        ]
    )
    fun log5TestMock(x: Double, lnx: Double, expected: Double) {
        val spy = Mockito.spy(Algebra(0.0))
        Mockito.`when`(spy.ln(x)).thenReturn(lnx)
        Mockito.`when`(spy.ln(5.0)).thenReturn(1.6094379124341)
        Assertions.assertEquals(expected, spy.log_5(x), 0.02 * max(1.0, abs(expected)))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            " 1 , 0 , 0 ",
            " 2.71828182845904 , 1 , 0.434294481903252 ",
            " 7.38905609893065 , 2 , 0.868588963806504 ",
            " 20.0855369231877 , 3 , 1.30288344570976 ",
            " 0.367879441171442 , -1 , -0.434294481903252 ",
            " 0.135335283236613 , -2 , -0.868588963806504 ",
            " 0.049787068367864 , -3 , -1.30288344570976 ",
            " 2 , 0.693147180559945 , 0.301029995663981 ",
            " 5 , 1.6094379124341 , 0.698970004336019 ",
            " 10 , 2.30258509299405 , 1 ",
            " 100 , 4.60517018598809 , 2 ",
            " 0.9 , -0.105360515657826 , -0.045757490560675 ",
            " 2.61828182845904 , 0.962518311983765 , 0.418016391625382 ",
            " 7.28905609893065 , 1.98637405875277 , 0.862671292712093 ",
            " 19.9855369231877 , 2.99500885811151 , 1.30071582032919 ",
            " 0.267879441171442 , -1.31721824605773 , -0.572060615725152 ",
            " 0.035335283236613 , -3.342873289119 , -1.45179142316615 ",
            " 1.9 , 0.641853886172395 , 0.278753600952829 ",
            " 4.9 , 1.58923520511658 , 0.690196080028514 ",
            " 9.9 , 2.29253475714054 , 0.99563519459755 ",
            " 99.9 , 4.60416968565451 , 1.99956548822598 ",
            " 1.1 , 0.095310179804325 , 0.041392685158225 ",
            " 2.81828182845904 , 1.03612741857483 , 0.449984420435711 ",
            " 7.48905609893065 , 2.01344276808316 , 0.874427083806527 ",
            " 20.1855369231877 , 3.00496635405951 , 1.30504030587298 ",
            " 0.467879441171442 , -0.759544620584711 , -0.329866037479239 ",
            " 0.235335283236613 , -1.44674404448335 , -0.628312955245509 ",
            " 0.149787068367864 , -1.89854053760754 , -0.824525679152586 ",
            " 2.1 , 0.741937344729377 , 0.322219294733919 ",
            " 5.1 , 1.62924053973028 , 0.707570176097936 ",
            " 10.1 , 2.31253542384721 , 1.00432137378264 ",
            " 100.1 , 4.60616968632117 , 2.00043407747932 ",
        ]
    )
    fun log10TestMock(x: Double, lnx: Double, expected: Double) {
        val spy = Mockito.spy(Algebra(0.0))
        Mockito.`when`(spy.ln(x)).thenReturn(lnx)
        Mockito.`when`(spy.ln(10.0)).thenReturn(2.30258509299405)
        Assertions.assertEquals(expected, spy.log_10(x), 0.02 * max(1.0, abs(expected)))
    }
}