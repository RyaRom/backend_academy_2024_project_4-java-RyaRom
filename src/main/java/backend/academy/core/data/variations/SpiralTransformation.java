package backend.academy.core.data.variations;

import backend.academy.core.data.image.Point;
import backend.academy.core.data.transformations.WeightedTransformation;

public final class SpiralTransformation extends WeightedTransformation {
    public SpiralTransformation(double weight) {
        super(weight);
    }

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        double newX = (1.0 / r) * (Math.cos(theta) + Math.sin(r));
        double newY = (1.0 / r) * (Math.sin(theta) - Math.cos(r));
        return new Point(weight() * newX, weight() * newY);
    }
}
