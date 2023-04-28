package racingcar;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CarList {
    private List<SimpleCar> cars;

    public CarList(List<SimpleCar> cars) {
        validateNonNegativePosition(cars);
        this.cars = cars;
    }

    public void runAllCars(){
        cars.forEach(SimpleCar::run);
    }

    public List<SimpleCar> getCopiedCars() {
        return this.cars.stream()
                .map(SimpleCar::copy)
                .collect(Collectors.toList());
    }

    public List<SimpleCar> extractWinners() {
        int maxPosition = cars.stream()
                .mapToInt(SimpleCar::getPosition)
                .max().orElseThrow(NoSuchElementException::new);

        return cars.stream()
                .filter(it -> it.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private void validateNonNegativePosition(List<SimpleCar> cars) {
        cars.stream()
                .filter(car -> car.getPosition() < 0)
                .findAny()
                .ifPresent(car -> {
                    throw new IllegalStateException("음수 포지션일 수 없습니다.");
                });
    }
}
