package dev.team.models;

public record Vector2D(Double x, Double y) {

    public Vector2D normalize() {
        double length = length();
        return new Vector2D(x / length, y / length);
    }

    public Vector2D scale(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }


    // Method to calculate the angle between this vector and another vector
    public double angleVectors(Vector2D vector) {
        // Calculate dot product
        double dotProduct = this.x * vector.x() + this.y * vector.y();

        // Calculate magnitudes
        double magnitudeA = Math.sqrt(this.x * this.x + this.y * this.y);
        double magnitudeB = Math.sqrt(vector.x() * vector.x() + vector.y() * vector.y());

        // Avoid division by zero
        if (magnitudeA == 0 || magnitudeB == 0) {
            return 0;
        }

        // Calculate cosine of the angle
        double cosTheta = dotProduct / (magnitudeA * magnitudeB);

        // Clamp value to avoid NaN due to floating-point precision issues
        cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));

        // Calculate angle in radians
        double angleInRadians = Math.acos(cosTheta);

        // Optionally, convert to degrees if needed

        return Math.toDegrees(angleInRadians);// Return the angle in degrees
    }

}
