package backend.academy.data.transformations;

import backend.academy.data.image.Point;
import backend.academy.data.image.RGB;
import java.util.function.UnaryOperator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IterativeFunction implements UnaryOperator<Point> {
    @Getter
    private final RGB rgb;

    private final AbstractTransformation composedTransformation;

    @Getter
    private final Double weight;

    public IterativeFunction(
        RGB rgb,
        AffineTransformation affineTransformation,
        Double weight,
        WeightedTransformation... transformations
    ) {
        this.rgb = rgb;
        this.weight = weight;
        this.composedTransformation = WeightedTransformation.compose(affineTransformation, transformations);
    }

    @Override
    public Point apply(Point point) {
        return composedTransformation.transform(point);
    }
}
