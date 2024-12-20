package backend.academy.core.data.transformations;

import backend.academy.core.data.image.Point;
import backend.academy.core.data.variations.AffineTransformation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class WeightedTransformation implements AbstractTransformation {
    private final double weight;

    public static AbstractTransformation compose(
        AffineTransformation affineFunc,
        AbstractTransformation... transformations
    ) {
        if (transformations.length == 0) {
            return affineFunc;
        }

        return point -> {
            Point result = new Point(0, 0);
            for (AbstractTransformation transformation : transformations) {
                Point transformed = affineFunc.compose(transformation).transform(point);
                result = result.sum(transformed);
            }
            return result;
        };
    }
}
